package com.marvin.component.config_test.builder;

import com.marvin.component.config_test.builder.definition.NodeDefinition;

public class TreeBuilder {
    
    protected NodeInterface tree;
    protected NodeDefinition root;
    protected NodeBuilder builder;
    
    public ParentNodeDefinitionInterface root(String name) throws Exception {
        return (ParentNodeDefinitionInterface) this.root(name, "array", null);
    }
    
    public NodeDefinition root(String name, String type, NodeBuilder builder) throws Exception {
        
        if(builder == null) {
            builder = new NodeBuilder();
        }
        
        this.builder = builder;
        
        this.root = this.builder.node(name, type);
        
        return this.root;
    }
    
    public NodeInterface buildTree() throws Exception {
        
        if(this.root == null) {
            throw new Exception("The configuration tree has no root node.");
        }
        
        if(this.tree != null) {
            return this.tree;
        }
        
        this.tree = this.root.getNode(true);
        return this.tree;
    }

}
