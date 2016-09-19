/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.controller;

import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.dialog.Request;
import com.marvin.component.util.ClassUtils;
import java.lang.reflect.Method;

/**
 *
 * @author Dr.Who
 */
public class ControllerResolver extends ContainerAware {
    
    protected ControllerReference instantiateController(Class<?> controller, Method meth) throws Exception {
        return new ControllerReference(controller.newInstance(), meth);
    }
    
    protected ControllerReference createController(String name) throws Exception {
        
        if (false == name.contains("::")) {
            throw new Exception(String.format("Unable to find controller '%s'.", name));
        }

        String[] split = name.split("::", 2);

        String className = split[0];
        String methodName = split[1];

        Class<?> clazz = ClassUtils.resolveClassName(className, null);
        Method action = ClassUtils.getMethod(clazz, methodName, (Class<?>[]) null);

        return instantiateController(clazz, action);
    }
    
    public ControllerReference resolveController(Request request) throws Exception {
        Object controller = request.getAttribute("_controller");
        
        if(controller == null) {
            return null;
        }
        
        if(controller instanceof ControllerReference) {
            // done;
            return (ControllerReference) controller;
        }
    
        if(controller instanceof String) {
            return createController((String) controller);
        }
        
        return null;
        
    }
}
