package com.marvin.component.factory;

import com.marvin.service.Classes;
import com.marvin.service.Parameters;
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
