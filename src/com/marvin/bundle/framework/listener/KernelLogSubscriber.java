/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.bundle.framework.listener;

import com.marvin.bundle.framework.console.event.ConsoleEvent;
import com.marvin.old.event.Event;
import com.marvin.old.event.subscriber.SubscriberInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 *
 * @author Dr.Who
 */
public class KernelLogSubscriber  implements SubscriberInterface<Event> {

    @Override
    public void recieve(String name, Event event) {
        System.out.println("KernelLogListener");
        System.out.format("event : %s\n", event);
    }

    @Override
    public boolean support(String name) {
        System.out.println(name);
        return name.matches("(.*)");
    }

    @Override
    public Map<String, Consumer<Event>> getSubscribedEvents() {
        Map<String, Consumer<Event>> map = new ConcurrentHashMap<>();
        map.put("test", this::test);
        return map;
    }
    
    private void test(Event event){
        System.out.println("test");
    }
}
