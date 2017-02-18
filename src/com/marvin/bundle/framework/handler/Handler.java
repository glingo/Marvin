package com.marvin.bundle.framework.handler;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import com.marvin.component.kernel.controller.ControllerReference;
import com.marvin.component.kernel.controller.ControllerResolverInterface;
import com.marvin.component.kernel.controller.argument.ArgumentResolver;

import com.marvin.bundle.framework.handler.event.FilterControllerArgumentsEvent;
import com.marvin.bundle.framework.handler.event.FilterControllerEvent;
import com.marvin.bundle.framework.handler.event.FilterModelAndViewEvent;
import com.marvin.bundle.framework.handler.event.FinishRequestEvent;
import com.marvin.bundle.framework.handler.event.GetModelAndViewEvent;
import com.marvin.bundle.framework.handler.event.GetModelAndViewForControllerResultEvent;
import com.marvin.bundle.framework.handler.event.GetModelAndViewForExceptionEvent;
import com.marvin.bundle.framework.handler.event.HandlerEvents;
import com.marvin.bundle.framework.handler.exception.ControllerNotFoundException;
import com.marvin.bundle.framework.handler.exception.HandlerException;
import com.marvin.bundle.framework.mvc.view.IView;
import com.marvin.bundle.framework.mvc.view.resolver.IViewResolver;

import com.marvin.bundle.framework.mvc.ModelAndView;

import com.marvin.component.event.dispatcher.DispatcherInterface;
import com.marvin.component.util.ReflectionUtils;
import java.util.logging.Logger;

// This is the handler for the front controller.
// it will handle every requests and actions that the user do.
// I thik that it should look for a ModelAndView response.
// and return it to the front controller who called it.
// which may be different in web than in swing or javafx.
// for instance, in web there is a servlet that the servlet container call.

public class Handler<R, T> {

    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    private final Stack<R> stack;
    private final ControllerResolverInterface ctrlResolver;
    private final DispatcherInterface dispatcher;
    private final ArgumentResolver argsResolver;
    private final IViewResolver viewResolver;
    
    public Handler(DispatcherInterface dispatcher, ControllerResolverInterface ctrlResolver, ArgumentResolver argsResolver, IViewResolver viewResolver, Stack<R> stack) {
        this.dispatcher   = dispatcher;
        this.ctrlResolver = ctrlResolver;
        this.argsResolver = argsResolver;
        this.viewResolver = viewResolver;
        this.stack        = stack;
    }
    
    public Handler(DispatcherInterface dispatcher, ControllerResolverInterface ctrlResolver, ArgumentResolver argsResolver, IViewResolver viewResolver) {
        this(dispatcher, ctrlResolver, argsResolver, viewResolver, new Stack<>());
    }
    
    /**
     * Delegate handle to a sub-method.
     * 
     * If capture is true, it will handle exceptions that occured.
     * If capture is false, it will finish and re-throw the exception.
     * 
     * @param request
     * @param capture boolean that tell handler to capture exceptions or not.
     * @return 
     * 
     * @throws Exception Only if one occured and we didn't captured it.
     */
    public ModelAndView handle(R request, T response, boolean capture) throws Exception {
        this.logger.info("Handling a request");
        
        ModelAndView mav = null;
        
        try { 
            
            // delegate to a private method the handle job.
            mav = handle(request, response);
            
        } catch(Exception e) {
            
            // look if we choose to capture exceptions.
            if(!capture) {
                // finish and re-throw it.
                finish(request, mav);
                throw e;
            }
            
            this.logger.info("Handler has capture an exception.");
            
            // handle it.
            mav = handleException(e, request, response);
        }
        
        this.logger.info("A request have been handled.");
        
        return mav;
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
    private ModelAndView handle(R request, T response) throws Exception {
        long start = new Date().getTime();
        
        // stack request
        this.stack.push(request);
        
        // Dispatch event Request ( getModelAndView )
        GetModelAndViewEvent<R, T> re = new GetModelAndViewEvent<>(this, request, response);
        this.dispatcher.dispatch(HandlerEvents.REQUEST, re);
        
        // If a response has been set, we do not need to do something else.
        if(re.hasModelAndView()) {
            // Just return the filtered response
            return filter(request, response, re.getModelAndView());
        }
        
        request = re.getRequest();
        
        // Find controller via resolver
        ControllerReference controller = this.ctrlResolver.resolveController(request);

        // At this point "controller" should be an object.
        if(controller == null){
            // if controller is null we have a problem.
            String msg = "No controller found for this request";
            this.logger.severe(msg);
            throw new ControllerNotFoundException(msg);
        }
        
        // Filter controller
        FilterControllerEvent<R, T> fe = new FilterControllerEvent<>(this, controller);
        this.dispatcher.dispatch(HandlerEvents.CONTROLLER, fe);
       
        // Get the controller in case that the event changed it.
        controller = fe.getController();
        
        // Resolve arguments to pass
        List<Object> arguments = this.argsResolver.getArguments(request, response, controller);
        
        // Filter controller arguments
        FilterControllerArgumentsEvent<R, T> argsEvent = new FilterControllerArgumentsEvent(this, controller, arguments);
        this.dispatcher.dispatch(HandlerEvents.CONTROLLER_ARGUMENTS, argsEvent);
        
        controller = argsEvent.getController();
        arguments  = argsEvent.getArguments();
        
        // invoke controller
        Object controllerResult = ReflectionUtils.invokeMethod(controller.getAction(), controller.getHolder(), arguments.toArray());
        
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
        
        if(!(controllerResult instanceof ModelAndView)) {
            // if a controller return an other type than ModelAndView 
            // we trigger an event to resolve the returned type.
        
            // get a ModelAndView for controller result
            GetModelAndViewForControllerResultEvent<R, T> event = new GetModelAndViewForControllerResultEvent(this, request, controllerResult);
            this.dispatcher.dispatch(HandlerEvents.RESPONSE, event);

            if(event.hasModelAndView()) {
                controllerResult = event.getModelAndView();
            }
            
            if(!(controllerResult instanceof ModelAndView)) {
                
                // We still not have a ModelAndView instance, let's throw an exception.
                String msg = String.format("The Controller must return a ModelAndView (%s given).", controllerResult);
                
                // Check if the result is null, they may forget a return in controller.
                if(null == controllerResult) {
                    msg += " Did you forget to add a return statement in your controller ?";
                }
                
                throw new Exception(msg);
            }
        }
        
        // filter la response ( pop request )
        ModelAndView mav = filter(request, response, (ModelAndView) controllerResult);
        
        // use a view resolver.
//        response.getWriter().print(controllerResponse);
        
        // flush here ?
//        response.flushBuffer();
        
        long end = new Date().getTime();
        String msg = String.format("%s executed in %s ms\n", controller, end - start);
        this.logger.fine(msg);

        return mav;
    }
    
    private ModelAndView filter(R request, T response, ModelAndView mav) throws Exception {
        
        this.logger.fine("Filtering ...");

        FilterModelAndViewEvent<R, T> fe = new FilterModelAndViewEvent(this, mav);
        this.dispatcher.dispatch(HandlerEvents.RESPONSE, fe);
        
        // this is ugly af !
        
        Object view = mav.getView();
        
        // render view ?
        if(view instanceof String) {
            view = this.viewResolver.resolveView(view.toString());
        }
        
        if(view == null) {
            throw new Exception("No view found for this request.");
        }
            
        ((IView) view).render(mav.getModel(), request, response);
        
        mav.setView(view);
        
        finish(request, mav);
        
        return fe.getModelAndView();
    }
    
    private void finish(R request, ModelAndView mav) throws Exception {
        this.logger.fine("Finishing request...");
        this.dispatcher.dispatch(HandlerEvents.FINISH_REQUEST, new FinishRequestEvent(this, request));
        this.stack.pop();
    }

    private ModelAndView handleException(Exception exception, R request, T response) throws Exception {
        
        this.logger.info("Handle an exception.");
        
        GetModelAndViewForExceptionEvent<R, T> exceptionEvent = new GetModelAndViewForExceptionEvent(this, request, response, exception);
        this.dispatcher.dispatch(HandlerEvents.EXCEPTION, exceptionEvent);
        
        exception = exceptionEvent.getException();
        exception.printStackTrace();
        
        if(!exceptionEvent.hasModelAndView()) {
            finish(request, exceptionEvent.getModelAndView());
            
            throw new Exception(exception);
        }

        ModelAndView mav = exceptionEvent.getModelAndView();

        if(exception instanceof HandlerException) {
            HandlerException exc = (HandlerException) exception;
//            response.sendError(exc.getStatusCode(), exc.getMessage());
            // send header
//            response.addHeader(null, null);
        }
        
        // traitement a effectuer dans un getresponseforexception event listener.
        exception.printStackTrace();
//        exception.printStackTrace(response.getWriter());
        
        return filter(request, response, mav);
    }
    
}
