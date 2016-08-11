package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeInterface;
import com.marvin.component.configuration.builder.node.EnumNode;
import com.marvin.component.configuration.builder.node.Node;

public class EnumNodeDefinition extends ScalarNodeDefinition {
    
    protected Object[] values;

    public EnumNodeDefinition(String name) {
        super(name);
    }
    
//    public EnumNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }
    
    
    @Override
    protected Node instatiateNode() {
//        return new EnumNode(this.name, this.parent, this.values);
        return new EnumNode(this.name, this.values);
    }
    
}
