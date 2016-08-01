/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.controller;

import com.marvin.old.util.classloader.ClassLoaderUtil;
import java.lang.reflect.Method;

/**
 *
 * @author Dr.Who
 */
public class ControllerResolver {
    
    protected Object instantiateController(Class<?> controller) throws Exception{
        return controller.newInstance();
    }
    
    public Controller createController(String name) throws Exception
    {
        if (false == name.contains("::")) {
            throw new Exception(String.format("Unable to find controller '%s'.", name));
        }
        
        String[] split = name.split("::", 2);
        
        String className = split[0];
        String methodName = split[1];

        Class<?> clazz = ClassLoaderUtil.loadClass(className);

        if (null == clazz) {
            throw new Exception(String.format("Class '%s' does not exist.", className));
        }
        
        Method action = clazz.getDeclaredMethod(methodName);
        
        if (action == null) {
            throw new Exception(String.format("Nor action found for %s", methodName));
        }

        return new Controller(this.instantiateController(clazz), action);
    }
}
