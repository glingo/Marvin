/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container;

import app.service.IService;
import app.service.TestServiceA;
import app.service.TestServiceB;
import app.service.TestServiceC;
import com.marvin.component.Services;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class ContainerProgrammaticTest {
    
    public static void main(String[] args) {
        String name = ContainerProgrammaticTest.class.getName();
        
        System.out.format("Debut du test %s. \n", name);
        System.out.println("--------------------");
        
        System.out.println("Creating a new Container");
        IContainer container = new Container();
        
        up(container);
        
        try {
            
            IService serviceA = container.get("test.service.a", TestServiceA.class);
            IService serviceB = container.get("test.service.b", TestServiceB.class);
            IService serviceC = container.get("test.service.c", TestServiceC.class);
            
            System.out.format("%s call sayHello\n", serviceA);
            serviceA.sayHello();
            
            System.out.format("%s call sayHello\n", serviceB);
            serviceB.sayHello();
            
            System.out.format("%s call sayHello\n", serviceC);
            serviceC.sayHello();
            
            
            System.out.println("Fin du test");
            System.out.println("--------------------");
            
        } catch (ContainerException ex) {
            Logger.getLogger(name).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void up(IContainer container) {
        
        System.out.format("Setting up mock services in container %s\n", container);
        
        container.set("test.service.a", Services.serviceA);
        container.set("test.service.b", Services.serviceB);
        container.set("test.service.c", Services.serviceC);
        
    }
}
