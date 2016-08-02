/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.event;

/**
 *
 * @author caill
 */
public class MyEventDispatcherTest {
    
    public static void main(String[] args) {
        EventDispatcher dispatcher = new EventDispatcher();
        
        dispatcher.addSubscriber(new MyEventSubscriber());
        dispatcher.addSubscriber(new MySecondEventSubscriber());
        
        dispatcher.dispatch(Events.START, new Event());
        dispatcher.dispatch(Events.BEFORE_LOAD, new Event());
        dispatcher.dispatch(Events.AFTER_LOAD, new Event());
        dispatcher.dispatch(Events.TERMINATE, new Event());
        
    }
}
