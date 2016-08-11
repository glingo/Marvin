package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeInterface;
import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.configuration.builder.node.ScalarNode;

public class ScalarNodeDefinition extends VariableNodeDefinition {

    public ScalarNodeDefinition(String name) {
        super(name);
    }
    
//    public ScalarNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }

    @Override
    protected Node instatiateNode() {
//        return new ScalarNode(this.name, this.parent);
        return new ScalarNode(this.name);
    }
    
}
