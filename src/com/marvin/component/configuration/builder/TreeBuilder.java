package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.builder.definition.NodeDefinition;
import com.marvin.component.configuration.builder.node.Node;

public class TreeBuilder {
    
    protected Node tree;
    protected NodeDefinition root;
    protected NodeBuilder builder;
    
    public NodeDefinition root(String name) throws Exception {
        return this.root(name, "array", null);
    }
    
    public NodeDefinition root(String name, String type, NodeBuilder builder) throws Exception {
        
        if(builder == null) {
            builder = new NodeBuilder();
        }
        
        this.builder = builder;
        
        this.root = this.builder.node(name, type);
        this.builder.setParent(root);
        
        return this.root;
    }
    
    public Node buildTree() throws Exception {
        
        if(this.root == null) {
            throw new Exception("The configuration tree has no root node.");
        }
        
        if(this.tree != null) {
            return this.tree;
        }
        
        this.tree = this.root.getNode(true);
        return this.tree;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TreeBuilder : \n");
        sb.append(this.root);
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
