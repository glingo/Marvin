package com.marvin.component.event.subscriber;

import com.marvin.component.event.Event;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Subscriber<T extends Event> implements SubscriberInterface<T> {

    @Override
    public abstract Map<String, Consumer<T>> getSubscribedEvents();
    
}
