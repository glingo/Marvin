package com.marvin.component.container.awareness;

import com.marvin.component.container.IContainer;
import com.marvin.component.container.exception.ContainerException;
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
        Object service = null;
        try {
            service = getContainer().get(id);
        } catch (ContainerException ex) {
            this.logger.severe(ex.getMessage());
        }
        
        return service;
    }
    
    protected <T> T get(String id, Class<T> type) {
        T service = null;
        try {
            service = getContainer().get(id, type);
        } catch (ContainerException ex) {
            this.logger.severe(ex.getMessage());
        }
        return service;
    }
    
}
