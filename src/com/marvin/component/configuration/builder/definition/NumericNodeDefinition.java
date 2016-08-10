package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.node.NodeInterface;

/**
 *
 * @author cdi305
 */
public abstract class NumericNodeDefinition extends ScalarNodeDefinition {

    protected Number max;
    protected Number min;
    
    public NumericNodeDefinition(String name) {
        super(name);
    }
    
    public NumericNodeDefinition(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
    public abstract void max(Number max) throws Exception;
    
    public abstract void min(Number min) throws Exception;
}
