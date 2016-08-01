package com.marvin.component.event.subscriber;

import java.util.Map;
import java.util.function.Consumer;

public abstract class Subscriber<T> implements SubscriberInterface<T> {

    @Override
    public abstract Map<String, Consumer<T>> getSubscribedEvents();
    
}
