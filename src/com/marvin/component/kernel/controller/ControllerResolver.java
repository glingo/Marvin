/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.controller;

import com.marvin.component.util.ClassUtils;
import java.lang.reflect.Method;

/**
 *
 * @author Dr.Who
 */
public class ControllerResolver {
    
    protected Object instantiateController(Class<?> controller) throws Exception {
        return controller.newInstance();
    }
    
    protected Controller createController(Class cl, Method meth) throws Exception {
        return new Controller(this.instantiateController(cl), meth);
    }
    
    public Controller resolveController(String name) throws Exception {
    
        if (false == name.contains("::")) {
            throw new Exception(String.format("Unable to find controller '%s'.", name));
        }
        
        String[] split = name.split("::", 2);
        
        String className = split[0];
        String methodName = split[1];
        
        Class<?> clazz = ClassUtils.resolveClassName(className, null);
        
        Method action = ClassUtils.getMethod(clazz, methodName, new Class[]{});

        return createController(clazz, action);
    }
}
