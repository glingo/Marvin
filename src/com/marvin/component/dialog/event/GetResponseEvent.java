package com.marvin.component.dialog.event;

import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;
import com.marvin.component.dialog.Response;

public class GetResponseEvent extends RequestHandlerEvent {

    private Response response;
    
    public GetResponseEvent(RequestHandler handler, Request request) {
        super(handler, request);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
        this.stopEventPropagation();
    }
    
    public boolean hasResponse() {
        return this.response != null;
    }
    
}
