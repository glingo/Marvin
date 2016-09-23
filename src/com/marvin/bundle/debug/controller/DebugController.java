package com.marvin.bundle.debug.controller;

import com.marvin.bundle.framework.controller.Controller;
import com.marvin.component.event.EventDispatcher;
import com.marvin.component.event.Event;
import java.util.HashMap;

public class DebugController extends Controller {
    
    public Object debugContainer(){
        HashMap<String, Object> context = new HashMap<>();
        context.put("container", this.getContainer());
        return this.render("com/marvin/bundle/debug/resources/view/debug_container.view", context);
    }
    
    public Object debugContainerHTML(){
        HashMap<String, Object> context = new HashMap<>();
        context.put("container", this.getContainer());
        return this.render("com/marvin/bundle/debug/resources/view/debug_container.html.view", context);
    }
    
    public Object debugEvents(){
        HashMap<String, Object> context = new HashMap<>();
        EventDispatcher dispatcher = this.get("event_dispatcher", EventDispatcher.class);
        
        dispatcher.dispatch("event_test", new Event());
        
        context.put("event_dispatcher", dispatcher);
        return this.render("com/marvin/bundle/debug/resources/view/debug_events.view", context);
    }
}
