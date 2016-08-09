package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.builder.NodeBuilder;
import com.marvin.component.configuration.node.NodeParentInterface;

/**
 *
 * @author cdi305
 */
public interface ParentNodeDefinitionInterface {
    
    NodeBuilder children();
    
//    ParentNodeDefinitionInterface append(NodeDefinition definition);
    
    void setBuilder(NodeBuilder builder);
    
    NodeParentInterface end();
}
