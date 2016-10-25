package com.marvin.component.factory;

import com.marvin.old.pattern.factory.IFactory;
import com.marvin.old.pattern.factory.factories.FactoryImpl;
import java.util.ArrayList;
import mock.services.IMockService;
import mock.services.MockServiceA;
import mock.services.MockServiceB;
import mock.services.MockServiceC;

public class FactoryTest {
    
    public static void main(String[] args) {
        IFactory factory = new FactoryImpl();
        
        Object[] pA = new Object[]{"serviceA", 0};
        IMockService serviceA = factory.instance(MockServiceA.class, pA);
        System.out.format("new instance : %s \n", serviceA);
        
        Object[] pB = new Object[]{serviceA, "serviceB", 1};
        IMockService serviceB = factory.instance(MockServiceB.class, pB);
        System.out.format("new instance : %s \n", serviceB);
        
        Object[] pC = new Object[]{serviceA, "serviceB", 1, new ArrayList()};
        IMockService serviceC = factory.instance(MockServiceC.class, pC);
        System.out.format("new instance : %s \n", serviceC);
        
    }
}
