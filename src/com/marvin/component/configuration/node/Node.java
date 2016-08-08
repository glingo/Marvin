package com.marvin.component.configuration.node;

/**
 *
 * @author cdi305
 */
public abstract class Node implements NodeInterface {
    
    protected String name;
    protected NodeInterface parent;
    protected boolean required;
    protected Object[] attributes;

    public Node(String name, NodeInterface parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public final String getPath() {
        String path = getName();
        
        if(this.parent != null) {
            path = this.getParent().getName() + "." + path;
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

    @Override
    public NodeInterface getParent() {
        return parent;
    }

    public void setParent(NodeInterface parent) {
        this.parent = parent;
    }

    public Object[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Object[] attributes) {
        this.attributes = attributes;
    }
    
}
