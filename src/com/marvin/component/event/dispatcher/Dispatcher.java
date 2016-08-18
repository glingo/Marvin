package com.marvin.component.event.dispatcher;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.marvin.component.event.subscriber.SubscriberInterface;

public abstract class Dispatcher<T> implements DispatcherInterface<T> {

    protected List<SubscriberInterface<T>> subscribers;

    public Dispatcher() {
        this.subscribers = new ArrayList<>();
    }
    
    public Dispatcher(List<SubscriberInterface<T>> subscribers) {
        this.subscribers = new ArrayList<>();
    }
    
    @Override
    public void addSubscriber(SubscriberInterface<T> sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void dispatch(String name, T event) {
        
        if (this.subscribers == null || this.subscribers.isEmpty()) {
            return;
        }
        
        this.subscribers.forEach((SubscriberInterface<T> subscriber) -> {
            
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
