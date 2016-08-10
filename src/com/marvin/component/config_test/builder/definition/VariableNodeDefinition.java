package com.marvin.component.config_test.builder.definition;

import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.NodeParentInterface;
import com.marvin.component.config_test.builder.node.VariableNode;

/**
 *
 * @author cdi305
 */
public class VariableNodeDefinition extends NodeDefinition {

    public VariableNodeDefinition(String name) {
        super(name);
    }
    
//    public VariableNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }
    
    protected NodeInterface instatiateNode(){
//        return new VariableNode(this.name, this.parent);
        return new VariableNode(this.name);
    }

    @Override
    public NodeInterface createNode() {
        NodeInterface node = instatiateNode();
        
        node.setRequired(this.required);
        
        return node;
    }
}
