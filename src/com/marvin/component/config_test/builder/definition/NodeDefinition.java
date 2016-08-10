package com.marvin.component.config_test.builder.definition;

import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.NodeParentInterface;
import com.marvin.component.config_test.builder.ParentNodeDefinitionInterface;
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
    protected boolean allowEmptyValue = true;
    protected HashMap<String, Object> attributes = new HashMap<>();
    
    protected ParentNodeDefinitionInterface parent;

    public NodeDefinition(String name) {
        this.name = name;
    }
    
    public ParentNodeDefinitionInterface end(){
        return this.parent;
    }

//    public NodeDefinition(String name, NodeParentInterface parent) {
//        this.name = name;
//        this.parent = parent;
//    }
    
    public void setParent(ParentNodeDefinitionInterface parent){
        this.parent = parent;
    }
    
    public NodeInterface getNode(boolean forceRoot){
        
        if(forceRoot) {
            this.parent = null;
        }
        
        // normalisation
        
        // validation
        
        NodeInterface node = this.createNode();
        
        // set attributes
        
        return node;
    }
    
    public NodeInterface getNode(){
        return this.getNode(false);
    }
    
    public NodeDefinition attribute(String key, Object attribute) {
        this.attributes.put(key, attribute);
        return this;
    }
    
    public NodeDefinition info(String info){
        return this.attribute("info", info);
    }
    
    public NodeDefinition defaultValue(Object value){
        this.useDefault = true;
        this.defaultValue = value;
        return this;
    }
    
    public NodeDefinition defaultNull(){
        return this.defaultValue(null);
    }
    
    public NodeDefinition defaultTrue(){
        return this.defaultValue(true);
    }
    
    public NodeDefinition defaultFalse(){
        return this.defaultValue(false);
    }
    
    public NodeDefinition required(){
        this.required = true;
        return this;
    }
    
    public NodeDefinition cannotBeEmpty(){
        this.allowEmptyValue = false;
        return this;
    }
    
    public abstract NodeInterface createNode();
    
}
