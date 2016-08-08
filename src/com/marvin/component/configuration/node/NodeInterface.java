/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.node;

/**
 *
 * @author cdi305
 */
public interface NodeInterface {
    
    String getName();
    
    String getPath();
    
    void setRequired(boolean required);
    
    boolean isRequired();
    
    boolean hasDefaultValue();
    
    NodeInterface getParent();
}
