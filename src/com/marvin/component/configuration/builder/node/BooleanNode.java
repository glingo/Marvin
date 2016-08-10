/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.NodeParentInterface;

/**
 *
 * @author cdi305
 */
public class BooleanNode extends ScalarNode {

    public BooleanNode(String name) {
        super(name);
    }
        
    public BooleanNode(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
}
