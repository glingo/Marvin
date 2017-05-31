package com.marvin.component.event.handler;

import com.marvin.component.event.Event;

@FunctionalInterface
public interface Handler<E extends Event> {
    
    void handle(E event) throws Exception;
}
