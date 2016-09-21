package com.marvin.bundle.framework.controller;

import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.dialog.Response;
import com.marvin.component.templating.Engine;
import com.marvin.component.templating.template.Template;
import java.io.StringWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends ContainerAware {
    
    protected Response render(String templateName, Map<String, Object> context) {
        Response response = new Response();
        try {
            
            StringWriter writer = new StringWriter();
            Engine engine = this.get("templating_engine", Engine.class);

            Template template = engine.getTemplate(templateName);
            
            template.evaluate(writer, context);
            
            writer.flush();
            
            response.setContent(writer.toString());
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    protected Response render(String name, Map context, String suffix) {

        if (suffix != null && !suffix.isEmpty()) {
            name += "." + suffix;
        }
        
        if(!name.endsWith(".view")) {
            name += ".view";
        }
        
        return render(name, context);
    }
}
