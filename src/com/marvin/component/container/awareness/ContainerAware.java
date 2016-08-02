/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.awareness;

import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.container.IContainer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
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
    
    protected <T> T get(String id, Class<T> type) {
        try {
            return this.container.get(id, type);
        } catch (ContainerException ex) {
            Logger.getLogger(ContainerAware.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
