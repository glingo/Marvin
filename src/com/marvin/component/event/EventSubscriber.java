package com.marvin.component.event;

import com.marvin.component.event.subscriber.SubscriberInterface;

public abstract class EventSubscriber implements SubscriberInterface<Event> {

    @Override
    public void recieve(String name, Event recieve) {
        
    }

    @Override
    public boolean support(String name) {
        return true;
    }

}
