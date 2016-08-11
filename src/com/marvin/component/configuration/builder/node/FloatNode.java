package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.NodeParentInterface;

public class FloatNode extends NumericNode {

    public FloatNode(String name) {
        super(name);
    }
    
    public FloatNode(String name, Node parent) {
        super(name, parent);
    }
    
}
