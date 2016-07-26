/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.event.dispatcher;

import com.marvin.old.event.subscriber.SubscriberInterface;
import java.util.ArrayList;
import java.util.List;

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
            if(subscriber.support(name)) {
                this.fire(subscriber, name, event);
            }
        });
    }
    
    protected void fire(SubscriberInterface<T> subscriber, String name, T event) {

        if (subscriber == null) {
            return;
        }
        
        if (event == null) {
            event = this.create();
        }

//        event.setDispatcher(this);
        
        System.out.format("Dispatch event %s\n", name);
        subscriber.recieve(name, event);
 
   }
    
}
