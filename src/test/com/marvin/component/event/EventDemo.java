package com.marvin.component.event;

import com.marvin.component.event.dispatcher.Dispatcher;
import com.marvin.component.event.handler.Handler;

public class EventDemo {
    
    public static void main(String[] args) throws Exception {
        
        Dispatcher dispatcher = new Dispatcher();
        
        dispatcher.register(TestEvent.class, (Handler<TestEvent>) (TestEvent event) -> {
            System.out.println(event.getToSay());
        });
        
        dispatcher.register(TestEvent.class, 2, (Handler<TestEvent>) (TestEvent event) -> {
            System.out.println(event.getToSay());
        });
        
        dispatcher.register(TestEvent.class, 3, (Handler<TestEvent>) (TestEvent event) -> {
            System.out.println(event.getToSay());
        });
        
        dispatcher.register(Test2Event.class, (Handler<Test2Event>) (Test2Event event) -> {
            System.out.println(event.getToSay());
        });
        
        dispatcher.register(Event.class, (Handler<Event>) (Event event) -> {
            System.out.println(event);
        });
        
        dispatcher.dispatch(new Test2Event());
        dispatcher.dispatch(new TestEvent("Hello world"));
        dispatcher.dispatch(new Event());
    }
}
