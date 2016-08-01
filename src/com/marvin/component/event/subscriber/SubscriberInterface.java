package com.marvin.component.event.subscriber;

import java.util.Map;
import java.util.function.Consumer;
//import java.util.function.Function;

public interface SubscriberInterface<T> {

    Map<String, Consumer<T>> getSubscribedEvents();
    
}
