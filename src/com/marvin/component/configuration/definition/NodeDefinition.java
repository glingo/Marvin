package com.marvin.component.configuration.definition;

import com.marvin.component.configuration.builder.NodeBuilder;
import com.marvin.component.configuration.node.NodeInterface;
import com.marvin.component.configuration.node.NodeParentInterface;
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
    
    protected NodeInterface parent;
    protected HashMap<String, Object> attributes;
    protected NodeBuilder builder;

    public NodeDefinition(String name) {
        this.name = name;
    }
        
    public NodeDefinition(String name, NodeInterface parent) {
        this.name = name;
        this.parent = parent;
    }
    
    public abstract NodeInterface createNode();
    
//    @Override
    public NodeInterface end(){
        return this.parent;
    }
    
//    @Override
    public NodeBuilder children() {
        return builder;
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
//
//    @Override
    public boolean isRequired() {
        return required;
    }
//
//    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isAllowEmptyValue() {
        return allowEmptyValue;
    }

    public void setAllowEmptyValue(boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
    }

//    @Override
    public NodeInterface getParent() {
        return parent;
    }

    public void setParent(NodeInterface parent) {
        this.parent = parent;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }
    
    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public void setAttribute(String key, Object attribute) {
        this.attributes.put(key, attribute);
    }

}
