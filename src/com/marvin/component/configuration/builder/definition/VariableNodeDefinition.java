package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.configuration.builder.node.VariableNode;

public class VariableNodeDefinition extends NodeDefinition {

    public VariableNodeDefinition(String name) {
        super(name);
    }
    
    protected Node instatiateNode(){
        return new VariableNode(this.name);
    }

    @Override
    public Node createNode() {
        Node node = instatiateNode();
        
        node.setRequired(this.required);
        
        return node;
    }
    
    
}
