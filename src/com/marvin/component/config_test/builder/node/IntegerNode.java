package com.marvin.component.config_test.builder.node;

import com.marvin.component.config_test.builder.NodeParentInterface;

public class IntegerNode extends NumericNode {

    public IntegerNode(String name) {
        super(name);
    }
    
    public IntegerNode(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
}
