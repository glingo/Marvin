package com.marvin.bundle.debug.controller;

import com.marvin.bundle.framework.controller.Controller;
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
}
