package com.marvin.bundle.framework.handler.event;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.framework.mvc.ModelAndView;

public class FilterModelAndViewEvent<R, T> extends HandlerEvent<R, T> {

    private ModelAndView mav;
    
    public FilterModelAndViewEvent(Handler<R, T> handler, ModelAndView mav) {
        super(handler);
        this.mav = mav;
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
    
}
