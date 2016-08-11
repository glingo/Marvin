/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.builder.node;

import java.util.concurrent.ConcurrentHashMap;

import com.marvin.component.configuration.builder.PrototypeNodeInterface;
import com.marvin.component.configuration.builder.NodeInterface;

public class ArrayNode extends Node implements PrototypeNodeInterface {

    protected boolean addIfNotSet = false;
    
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
    

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(super.toString());
//        this.children.values().forEach(builder::append);
//        return builder.toString(); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
