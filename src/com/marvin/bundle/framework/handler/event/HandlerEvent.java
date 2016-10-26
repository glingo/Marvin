package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.component.event.Event;

public class HandlerEvent<T, R> extends Event {

    private Handler handler;
    private T request;
    
    public HandlerEvent(Handler handler, T request) {
        this.handler = handler;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
    
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
}
