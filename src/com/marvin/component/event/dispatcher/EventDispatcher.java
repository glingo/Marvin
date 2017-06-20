package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import com.marvin.component.event.subscriber.SubscriberInterface;
import com.marvin.component.util.Assert;

public class EventDispatcher extends Dispatcher {

    public EventDispatcher() {
        super(event -> event.isPropagationStopped());
    }

    @Override
    public void dispatch(final Class eventType, Event event) throws Exception {
        Assert.notNull(event);
        event.setDispatcher(this);
        super.dispatch(eventType, event);
    }
    
    @Override
    public void register(SubscriberInterface subscriber) {
        subscriber.subscribe(this);
    }
}
