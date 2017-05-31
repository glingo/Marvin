package com.marvin.bundle.framework.mvc.event;

import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.controller.ControllerReference;

public class FilterControllerEvent<I> extends HandlerEvent<I, ControllerReference> {
    
    private ControllerReference controller;

    public FilterControllerEvent(Handler<I, ControllerReference> handler, ControllerReference controller) {
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
