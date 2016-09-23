package com.marvin.old.dialog.event;

import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.RequestHandler;
import com.marvin.old.dialog.Response;

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
