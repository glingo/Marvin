package com.marvin.component.resolver;

import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.container.exception.ContainerException;

public class ContainerResolver<O> extends ContainerAware implements Resolver<String, O> {
    
    private final Class<O> clazz;

    public ContainerResolver(Class<O> clazz) {
        this.clazz = clazz;
    }
    
    @Override
    public O resolve(String name) throws ContainerException {
        return getContainer().get(name, this.clazz);
    }
}
