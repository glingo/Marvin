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
public class PrototypedArrayNode extends ArrayNode {

    protected PrototypeNodeInterface prototype;
    
    public PrototypedArrayNode(String name) {
        super(name);
    }

    public PrototypedArrayNode(String name, NodeParentInterface parent) {
        super(name, parent);
    }

    public PrototypeNodeInterface getPrototype() {
        return prototype;
    }

    public void setPrototype(PrototypeNodeInterface prototype) {
        this.prototype = prototype;
    }
    
}
