package com.marvin.component.dialog.event;

import com.marvin.component.dialog.controller.ControllerReference;
import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;
import java.util.List;

public class FilterControllerArgumentsEvent extends FilterControllerEvent {

    private List<Object> arguments;

    public FilterControllerArgumentsEvent(RequestHandler handler, ControllerReference controller, List<Object> arguments, Request request) {
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
