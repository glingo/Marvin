package com.marvin.component.dialog.event;

import com.marvin.component.kernel.controller.ControllerReference;
import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;

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
