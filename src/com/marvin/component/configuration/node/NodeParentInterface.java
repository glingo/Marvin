/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.node;

import com.marvin.component.configuration.definition.NodeDefinition;

/**
 *
 * @author cdi305
 */
public interface NodeParentInterface {
    
    NodeParentInterface end();
    
    NodeParentInterface append(NodeDefinition definition);
//    void addChild(Node node);
    
}
