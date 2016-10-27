package com.marvin.bundle.framework.handler;

import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import com.marvin.bundle.framework.controller.ControllerReference;
import com.marvin.bundle.framework.controller.ControllerResolverInterface;
import com.marvin.bundle.framework.controller.argument.ArgumentResolver;

import com.marvin.bundle.framework.handler.event.FilterControllerArgumentsEvent;
import com.marvin.bundle.framework.handler.event.FilterControllerEvent;
import com.marvin.bundle.framework.handler.event.FilterResponseEvent;
import com.marvin.bundle.framework.handler.event.GetResponseEvent;
import com.marvin.bundle.framework.handler.event.GetResponseForControllerResultEvent;
import com.marvin.bundle.framework.handler.event.GetResponseForExceptionEvent;
import com.marvin.bundle.framework.handler.event.HandlerEvent;
import com.marvin.bundle.framework.handler.event.HandlerEvents;
import com.marvin.bundle.framework.handler.exception.ControllerNotFoundException;
import com.marvin.bundle.framework.handler.exception.HandlerException;

import com.marvin.component.event.dispatcher.DispatcherInterface;
import com.marvin.component.util.ReflectionUtils;

public class Handler<T, R> {
    
    private final Stack<T> stack;
    private final ControllerResolverInterface ctrlResolver;
    private final DispatcherInterface dispatcher;
    private final ArgumentResolver argsResolver;
    
    public Handler(DispatcherInterface dispatcher, ControllerResolverInterface ctrlResolver, ArgumentResolver argsResolver, Stack<T> stack) {
        this.dispatcher   = dispatcher;
        this.ctrlResolver = ctrlResolver;
        this.argsResolver = argsResolver;
        this.stack        = stack;
    }
    
    public Handler(DispatcherInterface dispatcher, ControllerResolverInterface ctrlResolver, ArgumentResolver argsResolver) {
        this(dispatcher, ctrlResolver, argsResolver, new Stack<>());
    }
    
    /**
     * Delegate handle to a sub-method.
     * 
     * If capture is true, it will handle exceptions that occured.
     * If capture is false, it will finish and re-throw the exception.
     * 
     * @param request
     * @param response
     * @param capture boolean that tell handler to capture exceptions or not.
     * 
     * @throws Exception Only if one occured and we didn't captured it.
     */
    public void handle(T request, R response, boolean capture) throws Exception {
        try { 
            
            // delegate to a private method the handle job.
            handle(request, response);
            
        } catch(Exception e) {
            
            // look if we choose to capture exceptions.
            if(!capture) {
                // finish and re-throw it.
                finish(request);
                throw e;
            }
            
            // handle it.
            handleException(e, request, response);
        }
    }
    
    /**
     * Will trigger events in order to delegate tasks.
     * 
     * We first stack the request in a Stack.
     * 
     * Trigger a first event to get a response.
     * If a response is set during this event we stop propagation and return it.
     * 
     * Otherwise we continue and trigger a 
     * 
     * @param request
     * @param response
     * 
     * @throws Exception If something wrong happened.
     */
    private R handle(T request, R response) throws Exception {
        long start = new Date().getTime();
        
        // stack request
        this.stack.push(request);
        
        // Dispatch event Request ( get response )
        GetResponseEvent<T, R> re = new GetResponseEvent(this, request);
        this.dispatcher.dispatch(HandlerEvents.REQUEST, re);
        
        // If a response has been set, we do not need to do something else.
        if(re.hasResponse()) {
            // Just return the filtered response
            return filterResponse(request, re.getResponse());
        }
        
        request = re.getRequest();
        
        // Find controller via resolver
        ControllerReference controller = this.ctrlResolver.resolveController(request);

        // At this point "controller" should be :
        // a Controller name or an object.
        if(controller == null){
            // if controller is null we have a problem.
            String msg = String.format(
                    "No controller found for %s", request);
            
            throw new ControllerNotFoundException(msg);
        }
        
        // Filter controller
        FilterControllerEvent<T, R> fe = new FilterControllerEvent(this, controller, request);
        this.dispatcher.dispatch(HandlerEvents.CONTROLLER, fe);
       
        // Get the controller in case that the event changed it.
        controller = fe.getController();
        
        // Resolve arguments to pass
        List<Object> arguments = this.argsResolver.getArguments(request, controller);
        
        // Filter controller arguments
        FilterControllerArgumentsEvent<T, R> argsEvent = new FilterControllerArgumentsEvent(this, controller, arguments, request);
        this.dispatcher.dispatch(HandlerEvents.CONTROLLER_ARGUMENTS, argsEvent);
        
        controller = argsEvent.getController();
        arguments  = argsEvent.getArguments();
        
        // invoke controller
        Object controllerResult = 
                ReflectionUtils.invokeMethod(controller.getAction(), 
                controller.getHolder(), arguments.toArray());

        
        // At this point the controller should have return :
        
        // ? A ModelAndView object.
        // ? A View object or his name.
        // ? A Model object
        // ? A HashMap object as the model
        
        
        // we need to check the returned value type and handle it 
        
        
        // typer la response
        // controller will not return a String but he should return 
        //  - the view ( as an object or name )
        //  - the model ( as a Map or complete it )

        
        
        
        if(!(controllerResult instanceof String)) {
            
            // get response for controller result
            GetResponseForControllerResultEvent rfce = new GetResponseForControllerResultEvent(this, request, controllerResult);
            this.dispatcher.dispatch(HandlerEvents.RESPONSE, rfce);

            if(rfce.hasResponse()) {
                controllerResult = rfce.getResponse();
            }
            
            if(!(controllerResult instanceof String)) {
                // should it return a response ? 
                String msg = String.format("The Controller must return a String (%s given).", controllerResult);
                
                if(null == response) {
                    msg += " Did you forget to add a return statement in your controller ?";
                }
                
                throw new Exception(msg);
            }
            
        }
        
        // filter la response ( pop request )
        response = filterResponse(request, response);
        
        // use a view resolver.
//        response.getWriter().print(controllerResponse);
        
        // flush here ?
//        response.flushBuffer();
        
        long end = new Date().getTime();
        System.out.format("%s executed in %s ms\n", controller, end - start);

        return response;
    }
    
    private void finish(T request) {
        this.dispatcher.dispatch(HandlerEvents.FINISH_REQUEST, new HandlerEvent(this, request));
        this.stack.pop();
    }
    
    private R filterResponse(T request, R response) {
        
        FilterResponseEvent<T, R> fe = new FilterResponseEvent(this, request, response);
        this.dispatcher.dispatch(HandlerEvents.RESPONSE, fe);
        
        finish(request);
        
        return fe.getResponse();
    }

    private void handleException(Exception exception, T request, R response) throws IOException {
       
//        if(response.isCommitted()) {
//            // nothing else to do.
//            return;
//        }
        
        GetResponseForExceptionEvent<T, R> exceptionEvent = new GetResponseForExceptionEvent(this, request, exception);
        this.dispatcher.dispatch(HandlerEvents.EXCEPTION, exceptionEvent);
        
        exception = exceptionEvent.getException();
        
        // in servlet we always have a response !
//        if(!exceptionEvent.hasResponse()) {
//            finishRequest(request);
//            
//            throw new Exception(exception);
//        }

//        response = exceptionEvent.getResponse();

        if(exception instanceof HandlerException) {
            HandlerException exc = (HandlerException) exception;
//            response.sendError(exc.getStatusCode(), exc.getMessage());
            // send header
//            response.addHeader(null, null);
        }
        
        filterResponse(request, response);
        
        // traitement a effectuer dans un getresponseforexception event listener.
        exception.printStackTrace();
//        exception.printStackTrace(response.getWriter());
    }
    
}
