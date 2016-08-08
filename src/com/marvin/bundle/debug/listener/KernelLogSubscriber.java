/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.bundle.debug.listener;

import com.marvin.component.event.Event;
import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 *
 * @author Dr.Who
 */
public class KernelLogSubscriber implements SubscriberInterface<Event> {

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
