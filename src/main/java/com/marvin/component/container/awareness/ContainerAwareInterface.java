package com.marvin.component.container.awareness;

import com.marvin.component.container.IContainer;

public interface ContainerAwareInterface {
    
    void setContainer(IContainer container);
    
    IContainer getContainer();
}
