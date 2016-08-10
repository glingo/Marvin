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
public class IntegerNode extends NumericNode {

    public IntegerNode(String name) {
        super(name);
    }
    
    public IntegerNode(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
}
