package com.marvin.bundle.framework.mvc.controller;

import com.marvin.component.container.IContainer;
import com.marvin.component.container.awareness.ContainerAwareInterface;
import com.marvin.component.mvc.controller.ControllerReference;
import com.marvin.component.mvc.controller.ControllerResolver;
import com.marvin.component.util.ClassUtils;
import java.lang.reflect.Method;

public class ContainerControllerResolver<T> extends ControllerResolver<T> implements ContainerAwareInterface {

    private IContainer container;
    private final ControllerNameParser parser;
    
    public ContainerControllerResolver(ControllerNameParser parser) {
        assert parser != null : "ControllerNameParser could not be null";
        this.parser = parser;
    }

    @Override
    protected ControllerReference instantiateController(Class controller, Method meth) throws Exception {
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
                        Method action = ClassUtils.getMethod(service.getClass(), fragments[1], (Class[]) null);
                        return new ControllerReference(service, action);
                }
            }
        }
        
        return super.createController(name);
    }

    @Override
    public void setContainer(IContainer container) {
        this.container = container;
    }

    @Override
    public IContainer getContainer() {
        return this.container;
    }
    

}
