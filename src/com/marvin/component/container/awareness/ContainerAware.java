package com.marvin.component.container.awareness;

import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.container.IContainer;

public abstract class ContainerAware implements ContainerAwareInterface {

    private IContainer container;
    
    @Override
    public void setContainer(IContainer container) {
        this.container = container;
    }

    @Override
    public IContainer getContainer() {
        return this.container;
    }
    
    protected Object get(String id) {
        try {
            return this.container.get(id);
        } catch (ContainerException ex) {
            return null;
        }
    }
    
    protected <T> T get(String id, Class<T> type) {
        try {
            return this.container.get(id, type);
        } catch (ContainerException ex) {
            return null;
        }
    }
    
}
