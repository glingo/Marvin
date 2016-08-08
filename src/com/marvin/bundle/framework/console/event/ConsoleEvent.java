package com.marvin.bundle.framework.console.event;

import com.marvin.bundle.framework.console.Console;
import com.marvin.component.event.Event;

public class ConsoleEvent extends Event {

    protected Console console;
    
    public ConsoleEvent(Console console) {
        super();
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }
    
}
