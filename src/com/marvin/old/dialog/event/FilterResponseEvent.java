package com.marvin.old.dialog.event;

import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.RequestHandler;
import com.marvin.old.dialog.Response;

public class FilterResponseEvent extends RequestHandlerEvent {

    private Response response;
    
    public FilterResponseEvent(RequestHandler handler, Request request, Response response) {
        super(handler, request);
        this.response = response;
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
