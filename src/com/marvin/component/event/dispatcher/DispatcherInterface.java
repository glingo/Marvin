package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import com.marvin.component.event.subscriber.SubscriberInterface;

public interface DispatcherInterface<T> {

    T create();
    
    void dispatch(String name, T event);

    void addSubscriber(SubscriberInterface<T> sub);
    
    void removeSubscriber(SubscriberInterface<T> sub);

}
