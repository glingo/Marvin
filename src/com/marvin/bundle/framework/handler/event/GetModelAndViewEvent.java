package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;

/**
 * This is the first Event that Handler emit.
 * 
 * @param <T>
 * @param <R> 
 */
public class GetResponseEvent<T, R> extends HandlerEvent<T, R> {

    private R response;
    
    public GetResponseEvent(Handler handler, T request) {
        super(handler, request);
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
