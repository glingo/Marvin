package com.marvin.component.configuration.builder.node;

public abstract class NumericNode extends ScalarNode {

    public NumericNode(String name) {
        super(name);
    }
    
    public NumericNode(String name, Node parent) {
        super(name, parent);
    }
    
}
