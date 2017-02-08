package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;

public class GetModelAndViewForExceptionEvent<R, T> extends GetModelAndViewEvent<R, T> {
    
    private Exception exception;

    public GetModelAndViewForExceptionEvent(Handler<R, T> handler, R request, T response, Exception exception) {
        super(handler, request, response);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}