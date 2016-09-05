package com.marvin.bundle.framework.controller;

import com.marvin.component.container.awareness.ContainerAwareInterface;
import com.marvin.component.kernel.controller.ControllerReference;
import com.marvin.component.util.ClassUtils;
import java.lang.reflect.Method;

public class ControllerResolver extends com.marvin.component.kernel.controller.ControllerResolver implements ContainerAwareInterface {

    private final ControllerNameParser parser;
    
    public ControllerResolver(ControllerNameParser parser) {
        this.parser = parser;
    }

    @Override
    protected ControllerReference instantiateController(Class<?> controller, Method meth) throws Exception {
        ControllerReference reference = super.instantiateController(controller, meth);
        
        if (reference.getHolder() instanceof ContainerAwareInterface) {
            ((ContainerAwareInterface) reference.getHolder()).setContainer(getContainer());
        }
        
        return reference;
    }
    
    @Override
    protected ControllerReference createController(String name) throws Exception {
        
        if(!name.contains("::")) {
            
            if(name.contains(":")){
                String[] fragments = name.split(":");
                
                switch(fragments.length) {
                    case 3 :    // a:b:c notation
                        name = this.parser.parse(name);
                        break;
                    case 2:     // service:method notation
                        Object service = getContainer().get(fragments[0]);
                        Method action = ClassUtils.getMethod(service.getClass(), fragments[1], new Class[]{});
                        return new ControllerReference(service, action);
                }
            }
        }
        
        return super.createController(name); //To change body of generated methods, choose Tools | Templates.
    }

}
