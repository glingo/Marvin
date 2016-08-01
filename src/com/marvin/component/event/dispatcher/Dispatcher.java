package com.marvin.component.event.dispatcher;

import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Dr.Who
 * @param <T>
 */
public abstract class Dispatcher<T> implements DispatcherInterface<T> {

    protected List<SubscriberInterface<T>> subscribers = new ArrayList<>();
    
    @Override
    public void addSubscriber(SubscriberInterface<T> sub) {
        subscribers.add(sub);
    }

    @Override
    public void dispatch(String name, T event) {
        
        if (subscribers == null || subscribers.isEmpty()) {
            return;
        }
        
        subscribers.forEach((SubscriberInterface<T> subscriber) -> {
            
            if (subscriber == null) {
                return;
            }
        
            subscriber.getSubscribedEvents().forEach((String key, Consumer<T> consumer) -> {
                if(key.equals(name)) {
                    consumer.accept(event);
                }
            });
        });
    }
    
}
