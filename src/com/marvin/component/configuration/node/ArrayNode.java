/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.node;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author cdi305
 */
public class ArrayNode extends Node implements PrototypeNodeInterface {

    protected boolean addIfNotSet = false;
    protected ConcurrentHashMap<String, NodeInterface> children;
    
    public ArrayNode(String name) {
        super(name);
    }
    
    public ArrayNode(String name, NodeInterface parent) {
        super(name, parent);
    }
    
    @Override
    public boolean hasDefaultValue() {
        return this.addIfNotSet;
    }
    
    public void addChild(NodeInterface child){
        this.children.putIfAbsent(child.getName(), child);
    }
    
}
