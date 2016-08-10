package com.marvin.component.config_test.builder.definition;

import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.NodeParentInterface;
import com.marvin.component.config_test.builder.node.EnumNode;
import com.marvin.component.config_test.builder.node.ScalarNode;

public class EnumNodeDefinition extends ScalarNodeDefinition {
    
    protected Object[] values;

    public EnumNodeDefinition(String name) {
        super(name);
    }
    
//    public EnumNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }
    
    
    @Override
    protected NodeInterface instatiateNode() {
//        return new EnumNode(this.name, this.parent, this.values);
        return new EnumNode(this.name, this.values);
    }
    
}
