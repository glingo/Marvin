package com.marvin.bundle.debug.controller;

import com.marvin.bundle.framework.console.event.ConsoleEvent;
import com.marvin.bundle.framework.console.event.ConsoleEvents;
import com.marvin.bundle.framework.controller.Controller;
import com.marvin.component.event.EventDispatcher;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public class DebugController extends Controller {
    
    public void debugContainer(){
        HashMap<String, Object> context = new HashMap<>();
        context.put("container", this.getContainer());
        this.render("com/marvin/bundle/debug/resources/view/debug_container.view", context);
    }
    
    public void debugContainerHTML(){
        HashMap<String, Object> context = new HashMap<>();
        context.put("container", this.getContainer());
        this.render("com/marvin/bundle/debug/resources/view/debug_container.html.view", context);
    }
    
    public void debugEvents(){
        HashMap<String, Object> context = new HashMap<>();
        EventDispatcher dispatcher = this.get("event_dispatcher", EventDispatcher.class);
        
        dispatcher.dispatch(ConsoleEvents.START, new ConsoleEvent(null));
        
        context.put("event_dispatcher", dispatcher);
        this.render("com/marvin/bundle/debug/resources/view/debug_events.view", context);
    }
}
