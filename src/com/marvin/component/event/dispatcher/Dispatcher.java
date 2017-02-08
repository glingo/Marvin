package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;

public abstract class Dispatcher<T extends Event> implements DispatcherInterface<T> {

    protected final Logger logger = Logger.getLogger(getClass().getName());

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
        this.logger.info(String.format("Subscriber %s has been added to %s.", sub.getClass().getName(), this.getClass().getName()));
    }
    
    @Override
    public void removeSubscriber(SubscriberInterface<T> subscriber) {
        
        if (getSubscribers().isEmpty() || !getSubscribers().contains(subscriber)) {
            return;
        }

        getSubscribers().remove(subscriber);
        
        this.logger.info(String.format("Subscriber %s has been removed from %s.", subscriber.getClass().getName(), this.getClass().getName()));
    }


    @Override
    public void dispatch(String name, T event) {
        
        String msg = String.format("%s is dispatching an event : %s ", this.getClass().getName(), name);
        this.logger.info(msg);
        
        getSubscribers().forEach((SubscriberInterface<T> subscriber) -> {
            
            if (subscriber == null) {
                this.logger.info(String.format("No subscriber found for %s", name));
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
                    if(this.stopCondition.test(event)) {
                        this.logger.info("We got a StopCondition true !");
                        break;
                    }
                }
                
                if(key.equals(name)) {
                    this.logger.info(String.format("We found a subscriber (%s) !", listener.getClass().getName()));
                    listener.accept(event);
                }
            }
        });
    }
    
}
