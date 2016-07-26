package com.marvin.old.event;

import java.util.Date;

public class Event {

    private long id;
    private boolean propagationStopped = false;
    private EventDispatcher dispatcher;

    public Event() {
        super();
        this.id = (new Date().getTime() + Math.round(Math.random() * 10));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

}
