/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.pattern.factory;

import com.marvin.component.util.Assert;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;

/**
 *
 * @author caill
 */
public abstract class FactoryUtils {

    // prevent instanciation
    private FactoryUtils() {
    }

    /**
     * Return a Class for the name given.
     *
     * @param className
     * @return Class
     */
    public static Class getClassForName(String className) {
        switch (className) {
            case "String":
                return String.class;
            case "byte":
                return byte.class;
            case "Byte":
                return Byte.class;
            case "short":
                return Short.class;
            case "char":
                return char.class;
            case "Character":
                return Character.class;
            case "int":
                return int.class;
            case "Integer":
                return Integer.class;
            case "long":
                return long.class;
            case "Long":
                return Long.class;
            case "float":
                return float.class;
            case "Float":
                return Float.class;
            case "double":
                return double.class;
            case "BigInteger":
                return BigInteger.class;
            case "BigDecimal":
                return BigDecimal.class;
            case "URL":
                return URL.class;
        }

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }

    }

    public static Class resolveType(Object argument) {
        Class type = Object.class;

        if (argument != null) {
            type = argument.getClass();
        }

        return type;
    }

    public static <T> T instantiate(Class<T> clazz) throws FactoryException {
        Assert.notNull(clazz, "Class must not be null");

        if (clazz.isInterface()) {
            throw new FactoryException(clazz, "Specified class is an interface");
        }
        
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new FactoryException(clazz, "Is it an abstract class?", ex);
        } catch (IllegalAccessException ex) {
            throw new FactoryException(clazz, "Is the constructor accessible?", ex);
        }
    }

}
