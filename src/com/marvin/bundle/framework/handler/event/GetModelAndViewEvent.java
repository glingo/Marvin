package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.framework.mvc.ModelAndView;

/**
 * This is the first Event that Handler emit.
 * 
 * It will stop his propagation if we set a ModelAndView object.
 * 
 */
public class GetModelAndViewEvent<R, T> extends HandlerEvent<R, T> {

    private ModelAndView mav;
    private R request;
    private T response;
    
    public GetModelAndViewEvent(Handler<R, T> handler, R request, T response) {
        super(handler);
        this.request = request;
        this.response = response;
    }

    public ModelAndView getModelAndView() {
        return mav;
    }

    public void setModelAndView(ModelAndView mav) {
        this.mav = mav;
        this.stopEventPropagation();
    }
    
    public boolean hasModelAndView() {
        return this.mav != null;
    }

    public R getRequest() {
        return request;
    }

    public void setRequest(R request) {
        this.request = request;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
    
}
