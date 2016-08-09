package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.node.NodeInterface;
import com.marvin.component.configuration.node.NodeParentInterface;
import com.marvin.component.configuration.node.VariableNode;

/**
 *
 * @author cdi305
 */
public class VariableNodeDefinition extends NodeDefinition {

    public VariableNodeDefinition(String name) {
        super(name);
    }
    
    public VariableNodeDefinition(String name, NodeInterface parent) {
        super(name, parent);
    }
    
    protected NodeInterface instatiateNode(){
        return new VariableNode(this.getName(), this.getParent());
    }

    @Override
    public NodeInterface createNode() {
        NodeInterface node = instatiateNode();
        
        node.setRequired(this.isRequired());
        
        return node;
    }
}
