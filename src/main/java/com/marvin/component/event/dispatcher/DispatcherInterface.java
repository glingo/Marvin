package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import com.marvin.component.event.handler.Handler;
import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Map;

public interface DispatcherInterface {

    <E extends Event> void dispatch(Class eventType, E event) throws Exception;
    
    default <E extends Event> void dispatch(E event) throws Exception {
        if (event == null) {
            return;
        }
        
         dispatch(event.getClass(), event);
    }
    
    void register(Class eventType, Integer priority, Handler handler);
    
    default void register(Class eventType, Handler handler) {
        register(eventType, 1, handler);
    }
    
    default void register(Class eventType, Map<Integer, Handler> handlers) {
        if (handlers == null || handlers.isEmpty()) {
            return;
        }
        
        handlers.forEach((priority, handler) -> register(eventType, priority, handler));
    }
    
    default void register(SubscriberInterface subscriber) {
        subscriber.subscribe(this);
    }
    
}
