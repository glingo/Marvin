package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.node.NodeInterface;
import com.marvin.component.configuration.node.NodeParentInterface;

/**
 *
 * @author cdi305
 */
public class ArrayNodeDefinition extends NodeDefinition {

     public ArrayNodeDefinition(String name) {
        super(name);
    }

    public ArrayNodeDefinition(String name, NodeInterface parent) {
        super(name, parent);
    }

    @Override
    public NodeInterface createNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
