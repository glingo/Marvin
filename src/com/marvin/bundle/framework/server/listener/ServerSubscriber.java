package com.marvin.bundle.framework.server.listener;

import com.marvin.bundle.framework.server.event.ServerEvent;
import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ServerSubscriber implements SubscriberInterface<ServerEvent> {

    @Override
    public Map<String, Consumer<ServerEvent>> getSubscribedEvents() {
        Map<String, Consumer<ServerEvent>> map = new ConcurrentHashMap<>();
        map.put("test", this::test);
        return map;
    }
    
    private void test(ServerEvent event){
        System.out.println("test");
    }

}
