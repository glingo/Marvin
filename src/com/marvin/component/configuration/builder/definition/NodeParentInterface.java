package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.builder.definition.NodeDefinition;
import com.marvin.component.configuration.builder.node.NodeInterface;

/**
 *
 * @author cdi305
 */
public interface NodeParentInterface extends Cloneable {
    
//    NodeParentInterface end();

    NodeParentInterface append(NodeDefinition definition) throws CloneNotSupportedException;
    
//    NodeDefinition variableNode(String name) throws Exception;
//     
//    NodeDefinition enumNode(String name) throws Exception;
//    
//    NodeDefinition floatNode(String name) throws Exception;
//    
//    NodeDefinition integerNode(String name) throws Exception;
//    
//    NodeDefinition booleanNode(String name) throws Exception;
//    
//    NodeDefinition scalarNode(String name) throws Exception;
//    
//    NodeDefinition arrayNode(String name) throws Exception;
}
