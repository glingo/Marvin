package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;

public class GetResponseForExceptionEvent<T, R> extends GetResponseEvent<T, R> {
    
    private Exception exception;

    public GetResponseForExceptionEvent(Handler<T, R> handler, T request, Exception exception) {
        super(handler, request);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}