package com.marvin.component.event;

import com.marvin.component.event.dispatcher.Dispatcher;
import java.util.Date;

public class Event {

    private long id;
    private boolean propagationStopped = false;
    private Dispatcher dispatcher;

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

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public boolean isPropagationStopped() {
        return propagationStopped;
    }

    public void stopEventPropagation() {
        this.propagationStopped = true;
    }

}
