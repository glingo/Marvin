/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.factory;

import com.marvin.component.Classes;
import com.marvin.component.Parameters;
import com.marvin.old.pattern.factory.IFactory;
import com.marvin.old.pattern.factory.factories.FactoryImpl;
import com.marvin.service.IService;

/**
 *
 * @author cdi305
 */
public class FactoryTest {
    
    public static void main(String[] args) {
        IFactory factory = new FactoryImpl();
        
        IService serviceA = factory.instance(Classes.serviceA, Parameters.serviceA);
        IService serviceB = factory.instance(Classes.serviceB, Parameters.serviceB);
        IService serviceC = factory.instance(Classes.serviceC, Parameters.serviceC);
        
        System.out.format("new instance : %s \n", serviceA);
        System.out.format("new instance : %s \n", serviceB);
        System.out.format("new instance : %s \n", serviceC);
        
    }
}
