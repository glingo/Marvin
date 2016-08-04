/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating;

import com.marvin.service.Services;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class TemplatingTest {

    public static void main(String[] args) {
        try {
            Engine engine = new Engine.Builder().build();
            Template compiledTemplate = engine.getTemplate("com/marvin/resources/view/test.view");
            
            Map<String, Object> context = new HashMap<>();
            context.put("name", "Mitchell");
            context.put("test", Services.serviceA);
            
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
