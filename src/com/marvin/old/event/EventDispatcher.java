package com.marvin.old.event;

import com.marvin.old.event.subscriber.SubscriberInterface;
import com.marvin.old.event.dispatcher.Dispatcher;
import java.util.Map;

public class EventDispatcher extends Dispatcher<Event> {

    @Override
    public void dispatch(String name, Event event) {

        if (event == null) {
            event = this.create();
        }

        event.setDispatcher(this);

//        this.doDispatch(subscribers, name, event);
    }

    protected void doDispatch(Map<String, SubscriberInterface<Event>> subscribers, String name, Event event) {

        if (subscribers == null || subscribers.isEmpty()) {
            return;
        }
        
        System.out.format("Dispatch event %s\n", name);

        subscribers.values().stream().forEach((subscriber) -> {
            if(subscriber.support(name))  {
                subscriber.recieve(name, event);
            }
        });
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
