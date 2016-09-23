package com.marvin.old.dialog.event;

import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.RequestHandler;
import com.marvin.old.dialog.controller.ControllerReference;
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
