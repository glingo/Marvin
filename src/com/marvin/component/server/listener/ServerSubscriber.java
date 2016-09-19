package com.marvin.component.server.listener;

import com.marvin.component.server.event.ServerEvent;
import com.marvin.component.server.event.ServerEvents;
import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ServerSubscriber implements SubscriberInterface<ServerEvent> {

    @Override
    public Map<String, Consumer<ServerEvent>> getSubscribedEvents() {
        Map<String, Consumer<ServerEvent>> map = new ConcurrentHashMap<>();
        map.put(ServerEvents.START, this::start);
        return map;
    }
    
    private void start(ServerEvent event){
        System.out.println("test");
    }

}
