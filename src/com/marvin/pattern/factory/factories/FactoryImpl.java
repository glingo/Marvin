package com.marvin.pattern.factory.factories;

import com.marvin.pattern.factory.Factory;
import com.marvin.pattern.factory.FactoryUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author cdi305
 */
public class FactoryImpl extends Factory {
    
    Collector<Object, ?, List<Class>> typeCollector = Collectors.mapping(FactoryUtils::resolveType, Collectors.toList());

    public FactoryImpl() {
    }
    
    @Override
    public <T> T instance(Class type, Object[] parameters) {
        
        if(type == null) {
            return null;
        }
        
        if(parameters == null){
            return instance(type);
        }
        
        Constructor<T>[] constructors = type.getConstructors();
        int paramLenght = parameters.length;
        Class[] types = Arrays.stream(parameters).collect(typeCollector).toArray(new Class[]{});
        
        
        Arrays.stream(constructors).filter((Constructor<T> c) -> {
            boolean countMatch = c.getParameterCount() == paramLenght;
//            Parameter[] cParams = c.getParameters();
            
//            Arrays.stream(cParams).forEach((Parameter c) -> {});
            
            
            return countMatch;
        }).forEach((Constructor<T> c) -> {
            
            Class[] cTypes = c.getParameterTypes();
            Parameter[] cParams = c.getParameters();
            
            System.out.format("------------------\n");

            System.out.format("constructor types : %s \n", Arrays.toString(cTypes));
            System.out.format("constructor params : %s \n", Arrays.toString(cParams));
            System.out.format("constructor TypeParameters : %s \n", Arrays.toString(c.getTypeParameters()));
            
            
            Arrays.stream(cParams).forEach((Parameter p) -> {
                
                System.out.format("\t------------------\n");
                System.out.format("\tParameter : %s \n", p);
                System.out.format("\tParameter name: %s \n", p.getName());
                System.out.format("\tParameter type: %s \n", p.getType());
                System.out.format("\tParameter implicit ? : %s \n", p.isImplicit());
                System.out.format("\tParameter Parameterized Type : %s \n", p.getParameterizedType());
                
            });
            
        });
        
        
        T product = null;
        
//        Class[] types = Arrays.stream(parameters).collect(typeCollector).toArray(new Class[]{});
        
//        try {
            
//            Constructor<T> constructor = type.getConstructor(types);
//            
////            Parameter[] params = constructor.getParameters();
////            Class[] paramTypes = constructor.getParameterTypes();
//            product = constructor.newInstance(parameters);
//            
//        } catch (NoSuchMethodException ex) {
//            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, "NoSuchMethodException", ex);
//        } catch (SecurityException ex) {
//            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, "SecurityException", ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, "InstantiationException", ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, "IllegalAccessException", ex);
//        } catch (IllegalArgumentException ex) {
//            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, "IllegalArgumentException", ex);
//        } catch (InvocationTargetException ex) {
//            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, "InvocationTargetException", ex);
//        }
        
        return product;
    }

    @Override
    public <T> T instance(Class type) {
        T product = null;
        
        try {
            
            product = (T) type.newInstance();
            
        } catch (InstantiationException ex) {
            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return product;
    }

}
