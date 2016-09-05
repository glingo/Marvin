package com.marvin.component.container.awareness;

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
        return getContainer().get(id);
    }
    
    protected <T> T get(String id, Class<T> type) {
        return getContainer().get(id, type);
    }
    
}
