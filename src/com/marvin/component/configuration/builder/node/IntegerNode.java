package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.NodeParentInterface;

public class IntegerNode extends NumericNode {

    public IntegerNode(String name) {
        super(name);
    }
    
    public IntegerNode(String name, Node parent) {
        super(name, parent);
    }
    
}
