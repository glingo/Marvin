package com.marvin.old.event.dispatcher;

import com.marvin.old.event.subscriber.SubscriberInterface;

public interface DispatcherInterface<T> {

    T create();
    void dispatch(String name, T event);

    void addSubscriber(SubscriberInterface<T> sub);
    void removeSubscriber(SubscriberInterface<T> sub);

}
