package com.marvin.component.configuration.builder.node;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author cdi305
 */
public abstract class Node {
    
    protected String name;
    protected Node parent;
    protected boolean required;
    protected HashMap<String, Object> attributes;
    
    protected LinkedHashMap<String, Node> children;
    
    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    public void addChild(Node child){
        
        if(this.children == null) {
            this.children = new LinkedHashMap<>();
        }
        
        this.children.putIfAbsent(child.getName(), child);
        child.setParent(this);
    }
    
    public final String getPath() {
        String path = getName();
        
        if(this.parent != null) {
            path = this.getParent().getName() + "." + path;
        }
        
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

    private int getRank(){
        int rank = 0;
        
        Node current = this;
        
        while(current.parent != null) {
            rank++;
            current = current.parent;
        }
        
        return rank;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        int rank = this.getRank();
        builder.append("|");
        
        for (int i = 0; i < rank; i++) {
            builder.append("-----");
        }
        
        builder.append(this.name);
        builder.append("\n");
        
        if(this.children != null) {
            this.children.values().forEach((Node child) -> {
                builder.append(child);
            });
        }
        
        return builder.toString();
    }
    
    
}
