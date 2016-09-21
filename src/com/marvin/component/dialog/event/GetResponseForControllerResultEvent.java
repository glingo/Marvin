package com.marvin.component.dialog.event;

import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;

public class GetResponseForControllerResultEvent extends RequestHandlerEvent {
    
    private Object response;

    public GetResponseForControllerResultEvent(RequestHandler handler, Request request, Object response) {
        super(handler, request);
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
        this.stopEventPropagation();
    }
    
    public boolean hasResponse(){
        return this.response != null;
    }
}
