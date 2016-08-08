package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.node.NodeInterface;
import com.marvin.component.configuration.node.NodeParentInterface;

/**
 *
 * @author cdi305
 */
public class EnumNodeDefinition extends ScalarNodeDefinition {

    public EnumNodeDefinition(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
}
