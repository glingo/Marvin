package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;

public class FinishRequestEvent<R, T> extends HandlerEvent<R, T> {
    
    private R request;

    public FinishRequestEvent(Handler<R, T> handler, R request) {
        super(handler);
        this.request = request;
    }

    public R getRequest() {
        return request;
    }

    public void setRequest(R request) {
        this.request = request;
    }

}
