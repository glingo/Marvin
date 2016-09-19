package com.marvin.bundle.debug.controller;

import com.marvin.component.shell.event.ShellEvent;
import com.marvin.component.shell.event.ShellEvents;
import com.marvin.bundle.framework.controller.Controller;
import com.marvin.component.event.EventDispatcher;
import com.marvin.component.dialog.Response;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public class DebugController extends Controller {
    
    public Response debugContainer(){
        HashMap<String, Object> context = new HashMap<>();
        context.put("container", this.getContainer());
        return this.render("com/marvin/bundle/debug/resources/view/debug_container.view", context);
    }
    
    public Response debugContainerHTML(){
        HashMap<String, Object> context = new HashMap<>();
        context.put("container", this.getContainer());
        return this.render("com/marvin/bundle/debug/resources/view/debug_container.html.view", context);
    }
    
    public Response debugEvents(){
        HashMap<String, Object> context = new HashMap<>();
        EventDispatcher dispatcher = this.get("event_dispatcher", EventDispatcher.class);
        
        dispatcher.dispatch(ShellEvents.START, new ShellEvent(null));
        
        context.put("event_dispatcher", dispatcher);
        return this.render("com/marvin/bundle/debug/resources/view/debug_events.view", context);
    }
}
