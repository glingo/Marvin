package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import com.marvin.component.event.subscriber.SubscriberInterface;

public class EventDispatcher extends Dispatcher<Event> {

    public EventDispatcher() {
        super(event -> event.isPropagationStopped());
    }

    @Override
    public void dispatch(final String name, Event event) throws Exception {
        if (event == null) {
            event = this.create();
            this.logger.info(String.format("%s has created an event.", this.getClass().getName()));
        }

        event.setDispatcher(this);
        
        super.dispatch(name, event);
    }
    
    public void register(SubscriberInterface subscriber) {
        subscriber.subscribe(this);
    }

    public Event create() {
        return (Event) new Event();
    }
}
