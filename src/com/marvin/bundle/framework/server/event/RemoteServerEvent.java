/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.bundle.framework.server.event;

import com.marvin.old.event.Event;
import java.net.Socket;

/**
 *
 * @author Dr.Who
 */
public class RemoteServerEvent extends Event {
    
    private Socket remote;

    public RemoteServerEvent(Socket remote) {
        super();
        this.remote = remote;
    }

    public Socket getRemote() {
        return remote;
    }

    public void setRemote(Socket remote) {
        this.remote = remote;
    }

}
