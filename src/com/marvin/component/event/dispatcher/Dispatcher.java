package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import java.util.List;
import java.util.ArrayList;

import com.marvin.component.event.subscriber.SubscriberInterface;
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

        getSubscribers().stream()
            .filter(subscriber -> subscriber != null)
            .forEach((SubscriberInterface<T> subscriber) -> {
                subscriber.getSubscribedEvents().entrySet().stream()
                    .filter(entry -> name.equals(entry.getKey()))
                    .map(entry -> entry.getValue())
                    .forEach(listener -> {
                        if(!hasStopCondition() || !this.stopCondition.test(event)) {
                            listener.accept(event);
                        }
                    });
            });
    }
    
}
