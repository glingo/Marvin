package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;

public class FilterResponseEvent<T, R> extends HandlerEvent<T, R> {

    private R response;
    
    public FilterResponseEvent(Handler handler, T request, R response) {
        super(handler, request);
        this.response = response;
    }

    public R getResponse() {
        return response;
    }

    public void setResponse(R response) {
        this.response = response;
        this.stopEventPropagation();
    }
    
    public boolean hasResponse() {
        return this.response != null;
    }
    
}
