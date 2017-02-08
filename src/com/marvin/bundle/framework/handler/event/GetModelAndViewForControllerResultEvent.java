package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.framework.mvc.ModelAndView;

public class GetModelAndViewForControllerResultEvent<R, T> extends HandlerEvent<R, T> {
    
    private final Object result;
    private ModelAndView mav;
    private R request;

    public GetModelAndViewForControllerResultEvent(Handler<R, T> handler, R request, Object result) {
        super(handler);
        this.result = result;
        this.request = request;
    }

    public Object getResult() {
        return result;
    }
    
    public ModelAndView getModelAndView() {
        return this.mav;
    }

    public void setModelAndView(ModelAndView mav) {
        this.mav = mav;
        this.stopEventPropagation();
    }
    
    public boolean hasModelAndView(){
        return this.mav != null;
    }

    public R getRequest() {
        return request;
    }

    public void setRequest(R request) {
        this.request = request;
    }
    
}
