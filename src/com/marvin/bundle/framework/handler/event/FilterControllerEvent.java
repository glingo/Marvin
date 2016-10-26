package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.controller.ControllerReference;
import com.marvin.bundle.framework.handler.Handler;

public class FilterControllerEvent<T, R> extends HandlerEvent<T, R> {
    
    private ControllerReference controller;

    public FilterControllerEvent(Handler<T, R> handler, ControllerReference controller, T request) {
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
