package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import com.marvin.component.event.handler.Handler;
import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Map;

public interface DispatcherInterface<E extends Event> {

    void dispatch(String name, E event) throws Exception;
    void register(String name, Integer priority, Handler<E> handler);
    
    default void register(String name, Handler<E> handler) {
        register(name, 1, handler);
    }
    
    default void register(String name, Map<Integer, Handler<E>> handlers) {
        if (handlers == null || handlers.isEmpty()) {
            return;
        }
        
        handlers.forEach((priority, handler) -> register(name, priority, handler));
    }
}
