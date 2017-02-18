package com.marvin.bundle.framework.handler.subscriber;

import com.marvin.bundle.framework.handler.event.GetModelAndViewForControllerResultEvent;
import com.marvin.bundle.framework.handler.event.HandlerEvent;
import com.marvin.bundle.framework.handler.event.HandlerEvents;
import com.marvin.bundle.framework.mvc.view.IView;
import com.marvin.bundle.framework.mvc.model.Model;
import com.marvin.bundle.framework.mvc.ModelAndView;
import com.marvin.component.event.EventSubscriber;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;

public class ViewResolverHandlerSubscriber extends EventSubscriber<HandlerEvent>{
    
    public static final String VIEW_PARAMETER = "_view";
    
    public void onResponse(HandlerEvent event){
        if(event instanceof GetModelAndViewForControllerResultEvent) {
            GetModelAndViewForControllerResultEvent e = (GetModelAndViewForControllerResultEvent) event;
            
            Object result = e.getResult();
            
            this.logger.log(Level.INFO, "Trying to resolve {0}", result);
            
            if(result == null) {
                // nothing to try.
                return;
            }
            
            ModelAndView mav = new ModelAndView();
            
            if(result instanceof IView) {
                this.logger.log(Level.INFO, "Resolving result as a view {0}", result);
                mav.setView(result);
                mav.setModel(new Model());
            }
            
            // If it is a String object so we will assume that is a view name.
            if(result instanceof String) {
                this.logger.log(Level.INFO, "Resolving result as a view name {0}", result);
                mav.setView(result);
                mav.setModel(new Model());
            }
            
            // if it is a Map we will assume that is a model.
            if(result instanceof Map) {
                this.logger.log(Level.INFO, "Resolving result as a model {0}", result);
                
                Map<String, Object> model = (Map<String, Object>) result;
                
                // if we set view as a model parameter.
                if(model.containsKey(VIEW_PARAMETER)) {
                    Object view = model.remove(VIEW_PARAMETER);
                    mav.setView(view);
                }
                
                mav.setModel(model);
            }
            
            e.setModelAndView(mav);
        }
    }

    @Override
    public Map<String, Consumer<HandlerEvent>> getSubscribedEvents() {
        Map<String, Consumer<HandlerEvent>> subscribed = new HashMap<>();
        subscribed.put(HandlerEvents.RESPONSE, this::onResponse);
        return subscribed;
    }
}
