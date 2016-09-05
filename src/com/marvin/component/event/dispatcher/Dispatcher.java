package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

public abstract class Dispatcher<T extends Event> implements DispatcherInterface<T> {

    private List<SubscriberInterface<T>> subscribers;
    
    private Predicate<T> stopCondition;

    public Dispatcher() {}
    
    public Dispatcher(List<SubscriberInterface<T>> subscribers) {
        this.subscribers = subscribers;
    }
    
    public Dispatcher(Predicate<T> stopCondition) {
        this.stopCondition = stopCondition;
    }
    
    public Dispatcher(List<SubscriberInterface<T>> subscribers, Predicate<T> stopCondition) {
        this.subscribers = subscribers;
        this.stopCondition = stopCondition;
    }

    public List<SubscriberInterface<T>> getSubscribers() {
        if(this.subscribers == null) {
            this.subscribers = new ArrayList<>();
        }
        return subscribers;
    }
    
    public boolean hasStopCondition(){
        return this.stopCondition != null;
    }
    
    @Override
    public void addSubscriber(SubscriberInterface<T> sub) {
        getSubscribers().add(sub);
    }

    @Override
    public void dispatch(String name, T event) {
        
        getSubscribers().forEach((SubscriberInterface<T> subscriber) -> {
            
            if (subscriber == null) {
                return;
            }
            
            Iterator<Map.Entry<String, Consumer<T>>> iterator = subscriber.getSubscribedEvents().entrySet().stream().filter((en) -> {
                return en.getKey().equals(name);
            }).iterator();
            
            while (iterator.hasNext()) {
                Map.Entry<String, Consumer<T>> entry = iterator.next();
                Object key = entry.getKey();
                Consumer<T> listener = entry.getValue();
                
                if(hasStopCondition()) {
                    if(stopCondition.test(event)) {
                        System.out.println("stop condition true !");
                        break;
                    }
                }
                
                if(key.equals(name)) {
                    listener.accept(event);
                }
            }
        });
    }
    
}
