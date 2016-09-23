package com.marvin.old.dialog.event;

import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.RequestHandler;
import com.marvin.old.dialog.controller.ControllerReference;

public class FilterControllerEvent extends RequestHandlerEvent {
    
    private ControllerReference controller;

    public FilterControllerEvent(RequestHandler handler, ControllerReference controller, Request request) {
        super(handler, request);
        this.controller = controller;
    }

    public ControllerReference getController() {
        return controller;
    }

    public void setController(ControllerReference controller) {
        this.controller = controller;
    }
    
}
