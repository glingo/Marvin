package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.NodeBuilder;
import com.marvin.component.configuration.builder.node.NodeInterface;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public abstract class NodeDefinition {
    
    protected String name;
    protected Object defaultValue;
    protected boolean useDefault;
    protected boolean required;
    protected boolean allowEmptyValue;
    protected HashMap<String, Object> attributes;
    
    protected NodeParentInterface parent;
    protected NodeBuilder builder;

    public NodeDefinition(String name) {
        this.name = name;
    }

    public NodeDefinition(String name, NodeParentInterface parent) {
        this.name = name;
        this.parent = parent;
    }
    
    public abstract NodeInterface createNode();
    
//    @Override
    public NodeParentInterface end(){
        return this.parent;
    }
    
//    @Override
    public NodeBuilder children() {
        return this.builder;
    }
    
    public NodeInterface getNode(){
        return this.getNode(false);
    }
    
    public NodeInterface getNode(boolean forceRoot){
        
        if(forceRoot) {
            this.setParent(null);
        }
        
        // normalisation
        
        // validation
        
        NodeInterface node = this.createNode();
        
        // set attributes
        
        return node;
    }
    
    public NodeDefinition attribute(String key, Object attribute) {
        if(this.attributes == null) {
            this.attributes = new HashMap<>();
        }
        this.attributes.put(key, attribute);
        return this;
    }
    
    public NodeDefinition info(String info){
        return this.attribute("info", info);
    }
    
//    @Override
    public void setBuilder(NodeBuilder builder) {
        this.builder = builder;
    }

//    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isUseDefault() {
        return useDefault;
    }

    public void setUseDefault(boolean useDefault) {
        this.useDefault = useDefault;
    }

    public boolean isAllowEmptyValue() {
        return allowEmptyValue;
    }

    public void setAllowEmptyValue(boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
    }

    public NodeParentInterface getParent() {
        return parent;
    }

    public void setParent(NodeParentInterface parent) {
        this.parent = parent;
    }

    public boolean isRequired() {
        return required;
    }
    
}
