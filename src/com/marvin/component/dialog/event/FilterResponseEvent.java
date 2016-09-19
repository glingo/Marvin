package com.marvin.component.dialog.event;

import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;
import com.marvin.component.dialog.Response;

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
