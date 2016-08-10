package com.marvin.component.config_test.builder.node;

import com.marvin.component.config_test.builder.NodeParentInterface;

public class FloatNode extends NumericNode {

    public FloatNode(String name) {
        super(name);
    }
    
    public FloatNode(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
}
