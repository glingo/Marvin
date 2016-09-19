package com.marvin.component.shell.event;

import com.marvin.component.shell.Shell;
import com.marvin.component.event.Event;

public class ShellEvent extends Event {

    protected Shell console;
    
    public ShellEvent(Shell console) {
        super();
        this.console = console;
    }

    public Shell getConsole() {
        return console;
    }

    public void setConsole(Shell console) {
        this.console = console;
    }
    
}
