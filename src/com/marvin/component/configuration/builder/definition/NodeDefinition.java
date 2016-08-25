package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeBuilder;
import com.marvin.component.configuration.builder.node.Node;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class NodeDefinition {
    
    protected String name;
    protected Object defaultValue;
    protected boolean useDefault;
    protected boolean required;
    protected boolean allowEmptyValue = true;
    protected HashMap<String, Object> attributes = new HashMap<>();
    
    protected NodeDefinition parent;
    protected LinkedHashMap<String, NodeDefinition> children;
    
    protected NodeBuilder nodeBuilder;

    public NodeDefinition(String name) {
        this.name = name;
    }
    
    public NodeDefinition end(){
        return this.parent;
    }
    
    public NodeDefinition append(NodeDefinition definition) throws CloneNotSupportedException {
        
        if(this.children == null) {
            this.children = new LinkedHashMap<>();
        }
        
        this.children.putIfAbsent(definition.name, definition);
        definition.setParent(this);
        return this;
    }
    
    protected NodeBuilder getNodeBuilder(){
        if(this.nodeBuilder == null) {
            this.nodeBuilder = new NodeBuilder();
            this.nodeBuilder.setParent(this);
        }
        
        return this.nodeBuilder;
    }
    
    public void setBuilder(NodeBuilder builder) {
        this.nodeBuilder = builder;
    }
    
    public void setParent(NodeDefinition parent){
        this.parent = parent;
    }
    
    public Node getNode(boolean forceRoot){
        
        if(forceRoot) {
            this.parent = null;
        }
        
        // normalisation
        
        // validation
        
        Node node = this.createNode();
        
        // set attributes
        
        return node;
    }
    
    public Node getNode(){
        return getNode(false);
    }
    
    public NodeDefinition attribute(String key, Object attribute) {
        this.attributes.put(key, attribute);
        return this;
    }
    
    public NodeDefinition info(String info){
        return attribute("info", info);
    }
    
    public NodeDefinition defaultValue(Object value){
        this.useDefault = true;
        this.defaultValue = value;
        return this;
    }
    
    public NodeDefinition defaultNull(){
        return defaultValue(null);
    }
    
    public NodeDefinition defaultTrue(){
        return defaultValue(true);
    }
    
    public NodeDefinition defaultFalse(){
        return defaultValue(false);
    }
    
    public NodeDefinition required(){
        this.required = true;
        return this;
    }
    
    public NodeDefinition cannotBeEmpty(){
        this.allowEmptyValue = false;
        return this;
    }
    
    public abstract Node createNode();
    
    public VariableNodeDefinition variableNode(String name) throws Exception {
        return getNodeBuilder().variableNode(name);
    }
     
    public EnumNodeDefinition enumNode(String name) throws Exception {
        return getNodeBuilder().enumNode(name);
    }
    
    public FloatNodeDefinition floatNode(String name) throws Exception {
        return getNodeBuilder().floatNode(name);
    }
    
    public IntegerNodeDefinition integerNode(String name) throws Exception {
        return getNodeBuilder().integerNode(name);
    }
    
    public BooleanNodeDefinition booleanNode(String name) throws Exception {
        return getNodeBuilder().booleanNode(name);
    }
    
    public ScalarNodeDefinition scalarNode(String name) throws Exception {
        return getNodeBuilder().scalarNode(name);
    }
    
    public ArrayNodeDefinition arrayNode(String name) throws Exception {
        return getNodeBuilder().arrayNode(name);
    }
    
    private int getRank(){
        int rank = 0;
        
        NodeDefinition current = this;
        
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
            this.children.values().forEach((NodeDefinition child) -> {
                builder.append(child);
            });
        }
        
        return builder.toString();
    }
    
    
}
