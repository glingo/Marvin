/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.awareness;

import com.marvin.component.container.IContainer;

/**
 *
 * @author cdi305
 */
public interface ContainerAwareInterface {
    
    void setContainer(IContainer container);
    
    IContainer getContainer();
}
