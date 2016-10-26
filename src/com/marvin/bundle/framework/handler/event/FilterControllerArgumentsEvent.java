package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.controller.ControllerReference;
import com.marvin.bundle.framework.handler.Handler;
import java.util.List;

public class FilterControllerArgumentsEvent<T, R> extends FilterControllerEvent<T, R> {

    private List<Object> arguments;

    public FilterControllerArgumentsEvent(Handler<T, R> handler, ControllerReference controller, List<Object> arguments, T request) {
        super(handler, controller, request);
        this.arguments = arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public List<Object> getArguments() {
        return arguments;
    }
    
}
