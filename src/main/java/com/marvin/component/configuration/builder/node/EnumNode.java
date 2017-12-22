package com.marvin.component.configuration.builder.node;

public class EnumNode extends ScalarNode {

    protected Object[] values;
    
    public EnumNode(String name) {
        super(name);
    }
    
    public EnumNode(String name, Object[] values) {
        super(name);
        this.values = values;
    }
    
}
