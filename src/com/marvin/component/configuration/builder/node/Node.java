package com.marvin.component.configuration.builder.node;

import com.marvin.component.config_test.builder.NodeParentInterface;

/**
 *
 * @author cdi305
 */
public abstract class Node implements NodeInterface {
    
    protected String name;
    protected NodeParentInterface parent;
    protected boolean required;
    protected Object[] attributes;
    
    public Node(String name) {
        this.name = name;
    }

    public Node(String name, NodeParentInterface parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public final String getPath() {
        String path = getName();
        
        if(this.parent != null) {
//            path = this.getParent().getName() + "." + path;
        }
        
        return path;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    public NodeParentInterface getParent() {
        return parent;
    }

    public void setParent(NodeParentInterface parent) {
        this.parent = parent;
    }

    public Object[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Object[] attributes) {
        this.attributes = attributes;
    }
    
}
