package com.marvin.component.config_test.builder.definition;

import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.NodeParentInterface;
import com.marvin.component.config_test.builder.node.ScalarNode;

public class ScalarNodeDefinition extends VariableNodeDefinition {

    public ScalarNodeDefinition(String name) {
        super(name);
    }
    
//    public ScalarNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }

    @Override
    protected NodeInterface instatiateNode() {
//        return new ScalarNode(this.name, this.parent);
        return new ScalarNode(this.name);
    }
    
}
