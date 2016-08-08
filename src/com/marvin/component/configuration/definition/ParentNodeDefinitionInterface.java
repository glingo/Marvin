package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.builder.NodeBuilder;

/**
 *
 * @author cdi305
 */
public interface ParentNodeDefinitionInterface {
    
    NodeBuilder children();
    
    ParentNodeDefinitionInterface append(NodeDefinition definition);
    
    void setBuilder(NodeBuilder builder);
}
