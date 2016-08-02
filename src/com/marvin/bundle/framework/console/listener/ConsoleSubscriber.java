package com.marvin.bundle.framework.console.listener;

import com.marvin.bundle.framework.console.event.ConsoleEvent;
import com.marvin.bundle.framework.console.event.ConsoleEvents;
import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ConsoleSubscriber implements SubscriberInterface<ConsoleEvent> {

    @Override
    public Map<String, Consumer<ConsoleEvent>> getSubscribedEvents() {
        Map<String, Consumer<ConsoleEvent>> map = new ConcurrentHashMap<>();
        map.put(ConsoleEvents.START, this::start);
        map.put(ConsoleEvents.BEFORE_LOAD, this::beforeLoad);
        map.put(ConsoleEvents.AFTER_LOAD, this::afterLoad);
        map.put(ConsoleEvents.TERMINATE, this::terminate);
        return map;
    }
    
    private void start(ConsoleEvent event){
        System.out.println("test");
    }
    
    private void beforeLoad(ConsoleEvent event){
        System.out.println("test");
    }
    
    private void afterLoad(ConsoleEvent event){
        System.out.println("test");
    }
    
    private void terminate(ConsoleEvent event){
        System.out.println("test");
    }
}
