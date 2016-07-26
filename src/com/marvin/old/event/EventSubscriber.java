package com.marvin.old.event;

import com.marvin.old.event.subscriber.SubscriberInterface;

public abstract class EventSubscriber implements SubscriberInterface<Event> {

    @Override
    public void recieve(String name, Event recieve) {
        
    }

    @Override
    public boolean support(String name) {
        return true;
    }

}
