/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 *
 * @author caill
 */
public class MySecondEventSubscriber extends EventSubscriber<Event> {

    @Override
    public Map<String, Consumer<Event>> getSubscribedEvents() {
        Map<String, Consumer<Event>> map = new ConcurrentHashMap<>();
        map.put(Events.START, this::start);
        map.put(Events.BEFORE_LOAD, this::beforeLoad);
        map.put(Events.AFTER_LOAD, this::afterLoad);
        map.put(Events.TERMINATE, this::terminate);
        return map;
    }
    
    private void start(Event event){
        System.out.println("second start");
        System.out.println(event);
    }
    
    private void beforeLoad(Event event){
        System.out.println("second beforeLoad");
        System.out.println(event);
    }
    
    private void afterLoad(Event event){
        System.out.println("second afterLoad");
        System.out.println(event);
    }
    
    private void terminate(Event event){
        System.out.println("second terminate");
        System.out.println(event);
    }
    
}
