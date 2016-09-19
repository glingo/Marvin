package com.marvin.component.dialog;

import com.marvin.component.event.EventDispatcher;
import com.marvin.component.kernel.controller.ArgumentResolver;
import com.marvin.component.kernel.controller.ControllerReference;
import com.marvin.component.kernel.controller.ControllerResolver;
import com.marvin.component.dialog.event.FilterControllerArgumentsEvent;
import com.marvin.component.dialog.event.RequestHandlerEvents;
import com.marvin.component.dialog.event.FilterControllerEvent;
import com.marvin.component.dialog.event.FilterResponseEvent;
import com.marvin.component.dialog.event.GetResponseEvent;
import com.marvin.component.dialog.event.GetResponseForControllerResultEvent;
import com.marvin.component.dialog.event.GetResponseForExceptionEvent;
import com.marvin.component.dialog.event.RequestHandlerEvent;
import com.marvin.component.util.ReflectionUtils;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class RequestHandler {
    
    private final Stack<Request> requestStack;
    private final ControllerResolver ctrlResolver;
    private final EventDispatcher dispatcher;
    private final ArgumentResolver argsResolver;
    
    public RequestHandler(EventDispatcher dispatcher, ControllerResolver ctrlResolver, ArgumentResolver argsResolver) {
        this.dispatcher = dispatcher;
        this.ctrlResolver = ctrlResolver;
        this.requestStack = new Stack<>();
        this.argsResolver = argsResolver;
    }
    
    public RequestHandler(EventDispatcher dispatcher, ControllerResolver ctrlResolver, Stack<Request> requestStack, ArgumentResolver argsResolver) {
        this.dispatcher = dispatcher;
        this.ctrlResolver = ctrlResolver;
        this.requestStack = requestStack;
        this.argsResolver = argsResolver;
    }

    private void finishRequest(Request request) {
        this.dispatcher.dispatch(RequestHandlerEvents.FINISH_REQUEST, new RequestHandlerEvent(this, request));
        this.requestStack.pop();
    }
    
    private Response filterResponse(Request request, Response response) {
        
        FilterResponseEvent filterEvent = new FilterResponseEvent(this, request, response);
        this.dispatcher.dispatch(RequestHandlerEvents.RESPONSE, filterEvent);
        
        finishRequest(request);
        
        return filterEvent.getResponse();
    }

    private Response handleException(Exception exception, Request request) throws Exception {
        GetResponseForExceptionEvent exceptionEvent = new GetResponseForExceptionEvent(this, request, exception);
        this.dispatcher.dispatch(RequestHandlerEvents.EXCEPTION, exceptionEvent);
        
        exception = exceptionEvent.getException();
        
        if(!exceptionEvent.hasResponse()) {
            finishRequest(request);
            
            throw new Exception(exception);
        }
        
        Response response = exceptionEvent.getResponse();
        
        try {
            return filterResponse(request, response);
        } catch(Exception e) {
            return response;
        }
    }
    
    public Response handle(Request request, boolean capture) throws Exception {
        
        try {
           return handlePart(request);
        } catch(Exception e) {
            
            if(!capture) {
                finishRequest(request);
                throw e;
            }
            
            return handleException(e, request);
        }
    }
    
    private Response handlePart(Request request) throws Exception {
        long start = new Date().getTime();
        
        // stack request
        requestStack.push(request);
        
        // Dispatch event Request ( get response )
        GetResponseEvent responseEvent = new GetResponseEvent(this, request);
        this.dispatcher.dispatch(RequestHandlerEvents.REQUEST, responseEvent);
        
        if(responseEvent.hasResponse()) {
            // return this.filterREsponse();
        }
        
        request = responseEvent.getRequest();
        
        // Find controller via resolver
        ControllerReference controller = this.ctrlResolver.resolveController(request);

        if(controller == null){
            throw new Exception("No controller for " + request.getUri());
        }
        
        // filter controller via event
        FilterControllerEvent filterEvent = new FilterControllerEvent(this, controller, request);
        this.dispatcher.dispatch(RequestHandlerEvents.CONTROLLER, filterEvent);
       
        controller = filterEvent.getController();
        
        // resolve arguments to pass
        List<Object> arguments = this.argsResolver.getArguments(request, controller);
        
        System.out.println("arguments :");
        System.out.println(arguments);
        
        // filter controller arguments via event
        FilterControllerArgumentsEvent argsEvent = new FilterControllerArgumentsEvent(this, controller, arguments, request);
        this.dispatcher.dispatch(RequestHandlerEvents.CONTROLLER_ARGUMENTS, argsEvent);
        
        controller = argsEvent.getController();
        arguments = argsEvent.getArguments();
        
        System.out.println("arguments :");
        System.out.println(arguments);
        
        // direct call controller
        Object response = ReflectionUtils.invokeMethod(controller.getAction(), controller.getHolder(), arguments.toArray());

        // typer la response
        if(!(response instanceof Response)) {
            
            // get response for controller result
            GetResponseForControllerResultEvent responseForControllerEvent = new GetResponseForControllerResultEvent(this, request, response);
            this.dispatcher.dispatch(RequestHandlerEvents.RESPONSE, responseForControllerEvent);

            if(responseForControllerEvent.hasResponse()) {
                response = responseForControllerEvent.getResponse();
            }
            
            if(!(response instanceof Response)) {
                // should it return a response ? 
                String msg = String.format("The Controller must return a Response (%s given).", response);
                
                if(null == response) {
                    msg += " Did you forget to add a return statement in your controller ?";
                }
                
                throw new Exception(msg);
            }
            
        }
        
        // filter la response ( pop request )
        response = filterResponse(request, (Response) response);
        
        long end = new Date().getTime();
        System.out.format("%s executed in %s ms\n", controller, end - start);
//        System.out.format("response : \n\t%s", response);

        return (Response) response;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("\n------")
            .append(super.toString())
            .append("------")
            .append("\n\t")
            .append(this.ctrlResolver)
            .append("\n\t")
            .append(this.dispatcher)
            .append("\n\t")
            .append(this.requestStack)
            .append("\n");
        
        return sb.toString();
    }

}
