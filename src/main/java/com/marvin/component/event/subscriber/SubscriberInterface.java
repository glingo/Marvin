package com.marvin.component.event.subscriber;

import com.marvin.component.event.dispatcher.DispatcherInterface;

@FunctionalInterface
public interface SubscriberInterface {
    
    void subscribe(DispatcherInterface dispatcher);
}
