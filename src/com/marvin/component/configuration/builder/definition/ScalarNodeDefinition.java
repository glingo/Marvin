package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.configuration.builder.node.ScalarNode;

public class ScalarNodeDefinition extends VariableNodeDefinition {

    public ScalarNodeDefinition(String name) {
        super(name);
    }

    @Override
    protected Node instatiateNode() {
        return new ScalarNode(this.name);
    }
    
}
