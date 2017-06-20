package com.marvin.component.event.handler;

@FunctionalInterface
public interface Handler<E> {
    void handle(E event) throws Exception;
}
