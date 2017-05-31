package com.marvin.bundle.debug.controller;

import com.marvin.bundle.framework.controller.tomove.Controller;
import com.marvin.component.event.Event;
import com.marvin.component.event.dispatcher.EventDispatcher;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        try {
            dispatcher.dispatch("event_test", new Event());
        } catch (Exception ex) {
            Logger.getLogger(DebugController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        context.put("event_dispatcher", dispatcher);
        return this.render("com/marvin/bundle/debug/resources/view/debug_events.view", context);
    }
}
