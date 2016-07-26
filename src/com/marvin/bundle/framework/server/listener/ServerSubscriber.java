package com.marvin.bundle.framework.server.listener;

import com.marvin.bundle.framework.server.event.ServerEvent;
import com.marvin.old.event.Event;
import com.marvin.old.event.subscriber.SubscriberInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ServerSubscriber implements SubscriberInterface<ServerEvent> {

    @Override
    public void recieve(String name, ServerEvent recieve) {
        System.out.println("event");
    }

    @Override
    public boolean support(String name) {
        System.out.println(name);
        return name.matches("^server_(.)");
    }

    @Override
    public Map<String, Consumer<ServerEvent>> getSubscribedEvents() {
        Map<String, Consumer<ServerEvent>> map = new ConcurrentHashMap<>();
        map.put("test", this::test);
        return map;
    }
    
    private void test(Event event){
        System.out.println("test");
    }

}
