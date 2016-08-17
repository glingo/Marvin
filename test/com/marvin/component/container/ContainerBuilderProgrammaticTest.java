/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container;

import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Parameter;
import com.marvin.component.container.config.Reference;
import com.marvin.service.IService;
import com.marvin.service.TestServiceA;
import com.marvin.service.TestServiceB;
import com.marvin.service.TestServiceC;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class ContainerBuilderProgrammaticTest {
    
    public static void main(String[] args) {
        
        // definition du service test.service.a
        Definition def_A = new Definition();
        def_A.setClassName("app.service.TestServiceA");
        def_A.setArguments(new Object[]{"Service A", 1});
        
        // definition du service test.service.b
        Reference ref_A = new Reference("test.service.a");
        Definition def_B = new Definition();
        def_B.setClassName("app.service.TestServiceB");
        def_B.setArguments(new Object[]{ref_A, "Service B", 2});
        
         // definition du service test.service.c
        Collection collec = Arrays.asList("Random string", 42);
        Definition def_C = new Definition();
        def_C.setClassName("app.service.TestServiceC");
        def_C.setArguments(new Object[]{ref_A, "Service C", 3, collec});
        
        // definition du service test.service.a.with.parameter
        Definition def_D = new Definition();
        def_D.setClassName("app.service.TestServiceA");
        def_D.setArguments(new Object[]{new Parameter("parameter.a"), 1});
        
        ContainerBuilder builder = new ContainerBuilder();
        
        builder.addParameter("parameter.a", "my parameter A");
        builder.addDefinition("test.service.a", def_A);
        builder.addDefinition("test.service.b", def_B);
        builder.addDefinition("test.service.c", def_C);
        builder.addDefinition("test.service.a.with.parameter", def_D);
        
        builder.build();
        Container container = builder.getContainer();
        
        try {
            
            IService a = container.get("test.service.a", TestServiceA.class);
            IService b = container.get("test.service.b", TestServiceB.class);
            IService c = container.get("test.service.c", TestServiceC.class);
            
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
            
            a.sayHello();
            b.sayHello();
            c.sayHello();
            
        } catch (ContainerException ex) {
            Logger.getLogger(ContainerBuilderProgrammaticTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
