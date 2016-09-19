package com.marvin.component.dialog.event;

import com.marvin.component.event.Event;
import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;

public class RequestHandlerEvent extends Event {

    protected RequestHandler handler;
    
    private Request request;

    public RequestHandlerEvent(RequestHandler handler, Request request) {
        this.handler = handler;
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public RequestHandler getHandler() {
        return handler;
    }

    public void setHandler(RequestHandler handler) {
        this.handler = handler;
    }
    
}
