/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container;

import com.marvin.component.container.exception.ContainerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mock.services.IMockService;
import mock.services.MockServiceA;
import mock.services.MockServiceB;
import mock.services.MockServiceC;
import mock.services.MockServiceD;

/**
 *
 * @author cdi305
 */
public class ContainerProgrammaticPersonnalTest {
    
    public static void main(String[] args) {
        String name = ContainerProgrammaticPersonnalTest.class.getName();
        
        System.out.format("Debut du test %s. \n", name);
        System.out.println("--------------------");
        
        System.out.println("Creating a new Container");
        IContainer container = new Container();
        
        up(container);
        
        IMockService serviceA = container.get("test.service.a", MockServiceA.class);
        IMockService serviceB = container.get("test.service.b", MockServiceB.class);
        IMockService serviceC = container.get("test.service.c", MockServiceC.class);
        IMockService serviceD = container.get("test.service.d", MockServiceD.class);

        System.out.format("%s call \n", serviceA);
        serviceA.mockMethod();

        System.out.format("%s call \n", serviceB);
        serviceB.mockMethod();

        System.out.format("%s call \n", serviceC);
        serviceC.mockMethod();

        System.out.println("Fin du test");
        System.out.println("--------------------");
        
    }
    
    public static void up(IContainer container) {
        
        System.out.format("Setting up mock services in container %s\n", container);
        
        container.set("mock.service.a", MockServiceA.class);
        container.set("mock.service.b", MockServiceB.class);
        container.set("mock.service.c", MockServiceC.class);
        container.set("mock.service.d", MockServiceD.class);
        
    }
}
