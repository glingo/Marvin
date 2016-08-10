package com.marvin.component.configuration.builder.node;

import com.marvin.component.config_test.builder.NodeParentInterface;
import java.util.concurrent.ConcurrentHashMap;

public class ArrayNode extends Node implements PrototypeNodeInterface {

    protected boolean addIfNotSet = false;
    protected ConcurrentHashMap<String, NodeInterface> children;
    
    public ArrayNode(String name) {
        super(name);
    }
    
    public ArrayNode(String name, NodeParentInterface parent) {
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
