package com.marvin.bundle.framework.subscriber;

import com.marvin.bundle.framework.mvc.event.FilterResultEvent;
import com.marvin.bundle.framework.mvc.event.HandlerEvents;
import com.marvin.component.event.dispatcher.DispatcherInterface;
import com.marvin.component.event.handler.Handler;
import com.marvin.component.event.subscriber.Subscriber;
import com.marvin.component.mvc.ModelAndView;
import com.marvin.component.mvc.view.ViewInterface;
import com.marvin.component.mvc.view.ViewResolverInterface;
import java.util.Objects;

public class ResponseSubscriber extends Subscriber {
    
    protected final ViewResolverInterface viewResolver;

    public ResponseSubscriber(ViewResolverInterface viewResolver) {
        this.viewResolver = viewResolver;
    }
    
    public Handler<FilterResultEvent> onResponse() {
        return e -> {
            Object result = e.getResult();
            Object request = e.getRequest();
            Object response = e.getResponse();
            
            if (!(result instanceof ModelAndView)) {
                throw new Exception("A Controller must return a ModelAndView, did you forget a return statement ?");
            }
            
            ModelAndView mav = (ModelAndView) result;
            Object view = mav.getView();
            
            if (view == null) {
                throw new Exception("No view for this controller");
            }
            
            if(view instanceof String) {
                view = this.viewResolver.resolve(view.toString());
            }

            mav.setView(view);

            // render view ?
            ((ViewInterface) view).render(e.getHandler(), mav.getModel(), request, response);
        };
    }
    
    @Override
    public void subscribe(DispatcherInterface dispatcher) {
        dispatcher.register(HandlerEvents.RESPONSE, onResponse());
    }
}
