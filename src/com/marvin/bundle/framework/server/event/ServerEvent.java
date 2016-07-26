package com.marvin.bundle.framework.server.event;

import com.marvin.old.event.Event;
import com.marvin.bundle.framework.server.Server;

public class ServerEvent extends Event {

    private Server server;

    public ServerEvent(Server server) {
        super();
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

}
