/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.definition.NodeDefinition;
import com.marvin.component.configuration.builder.NodeParentInterface;

/**
 *
 * @author cdi305
 */
public class VariableNode extends Node implements PrototypeNodeInterface {

    protected boolean defaultValueSet = false;
    protected boolean allowEmptyValue = true;
    protected Object defaultValue;
    
    public VariableNode(String name) {
        super(name);
    }

    public VariableNode(String name, NodeParentInterface parent) {
        super(name, parent);
    }

    @Override
    public boolean hasDefaultValue() {
        return this.defaultValueSet;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValueSet = true;
        this.defaultValue = defaultValue;
    }
    
}
