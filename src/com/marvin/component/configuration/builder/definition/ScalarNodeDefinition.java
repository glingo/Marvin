package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.node.NodeInterface;
import com.marvin.component.configuration.builder.node.ScalarNode;

/**
 *
 * @author cdi305
 */
public class ScalarNodeDefinition extends VariableNodeDefinition {

    public ScalarNodeDefinition(String name) {
        super(name);
    }
    
    public ScalarNodeDefinition(String name, NodeParentInterface parent) {
        super(name, parent);
    }

    @Override
    protected NodeInterface instatiateNode() {
        return new ScalarNode(getName(), getParent());
    }
    
}
