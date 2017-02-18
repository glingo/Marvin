package com.marvin.component.event.subscriber;

import com.marvin.component.event.Event;
import java.util.Map;
import java.util.function.Consumer;

public interface SubscriberInterface<T extends Event> {
    
    Map<String, Consumer<T>> getSubscribedEvents();
    
}
