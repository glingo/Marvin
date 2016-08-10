package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.node.NodeInterface;

/**
 *
 * @author cdi305
 */
public class BooleanNodeDefinition extends ScalarNodeDefinition {

    public BooleanNodeDefinition(String name) {
        super(name);
    }
    
    public BooleanNodeDefinition(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
}
