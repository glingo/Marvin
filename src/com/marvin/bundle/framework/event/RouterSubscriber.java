/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.bundle.framework.event;

import com.marvin.component.event.subscriber.SubscriberInterface;
import com.marvin.component.kernel.dialog.Request;
import com.marvin.component.kernel.dialog.event.GetResponseEvent;
import com.marvin.component.kernel.dialog.event.RequestHandlerEvent;
import com.marvin.component.kernel.dialog.event.RequestHandlerEvents;
import com.marvin.component.routing.Router;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author caill
 */
public class RouterSubscriber implements SubscriberInterface<RequestHandlerEvent> {

    private final Router router;

    public RouterSubscriber(Router router) {
        this.router = router;
    }
    
    public void onRequest(RequestHandlerEvent event){
        if(event instanceof GetResponseEvent) {
            Request request = event.getRequest();
            
            HashMap<String, Object> attributes = router.matchRequest(event.getRequest());
            request.addAttributes(attributes);
            
            event.setRequest(request);
        }
    }

    @Override
    public Map<String, Consumer<RequestHandlerEvent>> getSubscribedEvents() {
        Map<String, Consumer<RequestHandlerEvent>> subscribed = new HashMap<>();
        subscribed.put(RequestHandlerEvents.REQUEST, this::onRequest);
        return subscribed;
    }
    
}
