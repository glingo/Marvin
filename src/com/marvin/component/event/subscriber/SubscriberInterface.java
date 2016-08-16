package com.marvin.component.event.subscriber;

import java.util.Map;
import java.util.function.Consumer;

public interface SubscriberInterface<T> {

    Map<String, Consumer<T>> getSubscribedEvents();
    
}
