package com.marvin.component.kernel.controller;

import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.util.ClassUtils;
import java.lang.reflect.Method;

public abstract class ControllerResolver<T> extends ContainerAware implements ControllerResolverInterface<T> {
    
    protected ControllerReference instantiateController(Class<?> controller, Method meth) throws Exception {
        this.logger.info(String.format("Instanciating a controller (%s::%s)", controller, meth));
        return new ControllerReference(controller.newInstance(), meth);
    }
    
    protected ControllerReference createController(String name) throws Exception {
        
        if (false == name.contains("::")) {
            String msg = String.format("Unable to find controller '%s'.", name);
            throw new Exception(msg);
        }

        String[] split = name.split("::", 2);

        String className = split[0];
        String methodName = split[1];
        
        this.logger.info(String.format("Resolving a controller (%s::%s)", className, methodName));
        
        Class<?> clazz = ClassUtils.resolveClassName(className, null);
        Method action = ClassUtils.getMethod(clazz, methodName, (Class<?>[]) null);

        return instantiateController(clazz, action);
    }
    
    protected ControllerReference castController(Object controller) throws Exception {
        
        if(controller == null) {
            return null;
        }
        
        if(controller instanceof ControllerReference) { // done;
            return (ControllerReference) controller;
        }
    
        if(controller instanceof String) {
            return createController((String) controller);
        }
        
        return null;
    }
    
}
