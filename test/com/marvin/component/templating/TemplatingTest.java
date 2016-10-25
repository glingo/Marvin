package com.marvin.component.templating;

import com.marvin.component.templating.template.Template;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TemplatingTest {

    public static void main(String[] args) {
        try {
            Engine engine = new EngineBuilder().build();
            Template compiledTemplate = engine.getTemplate("com/marvin/resources/view/test.view");
            
            Map<String, Object> context = new HashMap<>();
            context.put("name", "Mitchell");
            context.put("test", new Object());
            
            PrintWriter writer = new PrintWriter(System.out, true);
            
//            Writer writer = new StringWriter();
            compiledTemplate.evaluate(writer, context);
            writer.flush();
            
//            String output = writer.toString();
            
//            System.out.println(writer.);
        } catch (Exception ex) {
            Logger.getLogger(TemplatingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
