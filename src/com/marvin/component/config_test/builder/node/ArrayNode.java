/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.config_test.builder.node;

import java.util.concurrent.ConcurrentHashMap;

import com.marvin.component.config_test.builder.PrototypeNodeInterface;
import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.NodeParentInterface;

public class ArrayNode extends Node implements PrototypeNodeInterface {

    protected boolean addIfNotSet = false;
    protected ConcurrentHashMap<String, NodeInterface> children;
    
    public ArrayNode(String name) {
        super(name);
    }
    
//    public ArrayNode(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }
    
//    @Override
    public boolean hasDefaultValue() {
        return this.addIfNotSet;
    }
    
    public void addChild(NodeInterface child){
        this.children.putIfAbsent(child.getName(), child);
        child.setParent(this);
    }

    
}
