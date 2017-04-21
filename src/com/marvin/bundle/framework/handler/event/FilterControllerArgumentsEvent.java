package com.marvin.bundle.framework.handler.event;

import com.marvin.component.kernel.controller.ControllerReference;
import com.marvin.bundle.framework.handler.Handler;
import java.util.List;

public class FilterControllerArgumentsEvent<R, T> extends FilterControllerEvent<R, T> {

    private List<Object> arguments;

    public FilterControllerArgumentsEvent(Handler<R, T> handler, ControllerReference controller, List<Object> arguments) {
        super(handler, controller);
        this.arguments = arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public List<Object> getArguments() {
        return arguments;
    }
}
