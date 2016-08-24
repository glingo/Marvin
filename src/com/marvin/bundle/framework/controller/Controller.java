package com.marvin.bundle.framework.controller;

import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.templating.Engine;
import com.marvin.component.templating.template.Template;
import java.io.Writer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class Controller extends ContainerAware {
    
    protected void render(String templateName, Map<String, Object> context){
        try {
            Writer writer = this.get("print_writer", Writer.class);
            Engine engine = this.get("templating_engine", Engine.class);

            Template template = engine.getTemplate(templateName);
            
            template.evaluate(writer, context);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
