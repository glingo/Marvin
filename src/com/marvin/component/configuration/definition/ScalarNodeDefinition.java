package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.node.NodeInterface;
import com.marvin.component.configuration.node.NodeParentInterface;
import com.marvin.component.configuration.node.ScalarNode;

/**
 *
 * @author cdi305
 */
public class ScalarNodeDefinition extends VariableNodeDefinition {

    public ScalarNodeDefinition(String name) {
        super(name);
    }
    
    public ScalarNodeDefinition(String name, NodeInterface parent) {
        super(name, parent);
    }

    @Override
    protected NodeInterface instatiateNode() {
        return new ScalarNode(getName(), getParent());
    }
    
}
