package com.marvin.component.container.awareness;

import com.marvin.component.container.IContainer;
import java.util.logging.Logger;

public abstract class ContainerAware implements ContainerAwareInterface {
    protected final Logger logger = Logger.getLogger(getClass().getName());

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
