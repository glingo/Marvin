package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.node.NodeInterface;

/**
 *
 * @author cdi305
 */
public class EnumNodeDefinition extends ScalarNodeDefinition {

    public EnumNodeDefinition(String name) {
        super(name);
    }
    
    public EnumNodeDefinition(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
}
