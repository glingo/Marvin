package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.node.NodeInterface;
import com.marvin.component.configuration.node.NodeParentInterface;

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
    
    public NumericNodeDefinition(String name, NodeInterface parent) {
        super(name, parent);
    }
    
    public abstract void max(Number max) throws Exception;
    
    public abstract void min(Number min) throws Exception;
}
