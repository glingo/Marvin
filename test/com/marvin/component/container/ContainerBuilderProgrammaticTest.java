package com.marvin.component.container;

import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Parameter;
import com.marvin.component.container.config.Reference;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import mock.services.IMockService;
import mock.services.MockServiceA;
import mock.services.MockServiceB;
import mock.services.MockServiceC;
import mock.services.MockServiceD;

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
            
            IMockService a = container.get("test.service.a", MockServiceA.class);
            IMockService b = container.get("test.service.b", MockServiceB.class);
            IMockService c = container.get("test.service.c", MockServiceC.class);
            IMockService d = container.get("test.service.c", MockServiceD.class);
            
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
            
            a.mockMethod();
            b.mockMethod();
            c.mockMethod();
            
        } catch (ContainerException ex) {
            Logger.getLogger(ContainerBuilderProgrammaticTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
