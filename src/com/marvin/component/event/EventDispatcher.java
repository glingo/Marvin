package com.marvin.component.event;

import com.marvin.component.event.dispatcher.Dispatcher;
import com.marvin.component.event.subscriber.SubscriberInterface;

public class EventDispatcher extends Dispatcher<Event> {

    @Override
    public void dispatch(String name, Event event) {

        if (event == null) {
            event = this.create();
        }

        event.setDispatcher(this);

        super.dispatch(name, event);
    }

    @Override
    public void removeSubscriber(SubscriberInterface<Event> subscriber) {
        if (subscribers == null || subscribers.isEmpty() || !subscribers.contains(subscriber)) {
            return;
        }

        subscribers.remove(subscriber);
    }

    @Override
    public Event create() {
        return new Event();
    }
}
