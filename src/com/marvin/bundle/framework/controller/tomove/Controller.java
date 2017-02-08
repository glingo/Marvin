package com.marvin.bundle.framework.controller.tomove;

import com.marvin.component.container.awareness.ContainerAware;
//import com.marvin.bundle.templating.Engine;
//import com.marvin.bundle.templating.template.Template;
import java.io.StringWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// c'est plutot un renderer !

// un controller devrait renvoyer <String, HashMap> 
// (view path/name, model) 
// ou et quoi.

public class Controller extends ContainerAware {
    
    protected Object render(String templateName, Map<String, Object> context) {
        StringWriter writer = new StringWriter();
//        Response response = new Response();
        try {
            
//            Engine engine = this.get("templating_engine", Engine.class);
//
//            Template template = engine.getTemplate(templateName);
//            
//            template.evaluate(writer, context);
            
            writer.flush();
            
//            response.setContent(writer.toString());
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return writer.toString();
    }
    
    protected Object render(String name, Map context, String suffix) {

        if (suffix != null && !suffix.isEmpty()) {
            name += "." + suffix;
        }
        
        if(!name.endsWith(".view")) {
            name += ".view";
        }
        
        return render(name, context);
    }
}
