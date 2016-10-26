package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;

public class FinishRequestEvent<T, R> extends HandlerEvent<T, R> {

    public FinishRequestEvent(Handler<T, R> handler, T request) {
        super(handler, request);
    }

}
