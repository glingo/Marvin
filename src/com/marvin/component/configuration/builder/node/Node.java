package com.marvin.component.configuration.builder.node;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author cdi305
 */
public abstract class Node {
    
    protected String name;
    protected Node parent;
    protected boolean required;
    protected boolean allowOverWrite = true;
    protected HashMap<String, Object> attributes;
    
    protected LinkedHashMap<String, Node> children;
    
    
    protected List<Function<Object, Object>> normalizationClosures;
    protected List<Function<Object, Object>> finalValidationClosures;
    
    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }
    
    protected Object preNormalize(Object value) {
        return value;
    }
    
    final public Object normalize(Object value) {
        
        value = this.preNormalize(value);
        
        for (Function<Object, Object> closure : normalizationClosures) {
            value = closure.apply(value);
        }
        
        // replaces with equivalent values
        // dans un tableau de valeur
        
        this.validateType(value);
        
        return this.normalizeValue(value);
    }
    
    final public Object merge(Object left, Object right) throws Exception {
        
        if(!this.allowOverWrite) {
            String msg = String.format("Configuration path %s, can not be overwritten. "
                    + "You have to define all options for this path, "
                    + "and any of its sub-paths in one configuration section.", this.getPath());
            throw new Exception(msg);
        }
        
        // validate type of left side object
        this.validateType(left);
        
        // validate type of right side object
        this.validateType(right);
        
        return this.mergeValues(left, right);
    }
    
    final public Object finalize(Object value) throws Exception {
        
        this.validateType(value);
        
        value = this.finalizeValue(value);
        
        for (Function<Object, Object> closure : finalValidationClosures) {
            value = closure.apply(value);
        }
        
        return value;
    }
    
    abstract protected void validateType(Object value);
    
    abstract protected Object normalizeValue(Object value);
    
    abstract protected Object mergeValues(Object left, Object right);

    abstract protected Object finalizeValue(Object value) throws Exception;
    
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

    public void setAllowOverWrite(boolean allowOverWrite) {
        this.allowOverWrite = allowOverWrite;
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
