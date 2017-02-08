package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.component.event.Event;

public class HandlerEvent<R, T> extends Event {

    private Handler<R, T> handler;
    
    public HandlerEvent(Handler<R, T> handler) {
        this.handler = handler;
    }

    public Handler<R, T> getHandler() {
        return handler;
    }

    public void setHandler(Handler<R, T> handler) {
        this.handler = handler;
    }
    
}
