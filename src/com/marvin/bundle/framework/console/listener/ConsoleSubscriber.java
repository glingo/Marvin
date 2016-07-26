package com.marvin.bundle.framework.console.listener;

import com.marvin.bundle.framework.console.event.ConsoleEvent;
import com.marvin.old.event.subscriber.SubscriberInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ConsoleSubscriber implements SubscriberInterface<ConsoleEvent> {

    @Override
    public void recieve(String name, ConsoleEvent recieve) {
        System.out.println("event");
    }

    @Override
    public boolean support(String name) {
        System.out.println(name);
        return name.matches("^console_(.)");
    }

    @Override
    public Map<String, Consumer<ConsoleEvent>> getSubscribedEvents() {
        Map<String, Consumer<ConsoleEvent>> map = new ConcurrentHashMap<>();
        map.put("test", this::test);
        return map;
    }
    
    private void test(ConsoleEvent event){
        System.out.println("test");
    }
}
