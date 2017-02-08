package com.marvin.bundle.framework.handler.event;

import com.marvin.component.kernel.controller.ControllerReference;
import com.marvin.bundle.framework.handler.Handler;

public class FilterControllerEvent<R, T> extends HandlerEvent<R, T> {
    
    private ControllerReference controller;

    public FilterControllerEvent(Handler<R, T> handler, ControllerReference controller) {
        super(handler);
        this.controller = controller;
    }

    public ControllerReference getController() {
        return controller;
    }

    public void setController(ControllerReference controller) {
        this.controller = controller;
    }
    
}
