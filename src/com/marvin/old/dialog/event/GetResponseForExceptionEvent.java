package com.marvin.old.dialog.event;

import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.RequestHandler;

public class GetResponseForExceptionEvent extends GetResponseEvent {
    
    private Exception exception;

    public GetResponseForExceptionEvent(RequestHandler handler, Request request, Exception exception) {
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