package com.marvin.component.event;

import com.marvin.component.event.dispatcher.Dispatcher;
import com.marvin.component.event.subscriber.SubscriberInterface;

public class EventDispatcher extends Dispatcher<Event> {

    public EventDispatcher() {
        super((Event event) -> {return event.isPropagationStopped();});
    }

    @Override
    public void dispatch(final String name, Event event) {
        
        if (event == null) {
            event = this.create();
        }

        event.setDispatcher(this);
        
        super.dispatch(name, event);
    }

    @Override
    public void removeSubscriber(SubscriberInterface<Event> subscriber) {
        
        if (getSubscribers().isEmpty() 
                || !getSubscribers().contains(subscriber)) {
            
            return;
        }

        getSubscribers().remove(subscriber);
    }

    @Override
    public Event create() {
        return new Event();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
            .append("\n-----")
            .append(this.getClass())
            .append("-----")
            .append("\n\t")
            .append(getSubscribers())
            .append("\n");
        return sb.toString();
    }
    
}
