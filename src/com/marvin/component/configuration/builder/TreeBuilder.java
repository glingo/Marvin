/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.definition.NodeDefinition;
import com.marvin.component.configuration.node.NodeInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class TreeBuilder {
    
    protected NodeInterface tree;
    protected NodeDefinition root;
    protected NodeBuilder builder;
    
    public NodeDefinition root(String name) {
        return this.root(name, "array", null);
    }
    
    public NodeDefinition root(String name, String type, NodeBuilder builder) {
        
        if(builder == null) {
            builder = new NodeBuilder();
        }
        
        this.builder = builder;
        
        try {
            this.root = this.builder.node(name, type);
        } catch (Exception ex) {
            Logger.getLogger(TreeBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.root;
    }
    
    public NodeInterface buildTree() throws Exception {
        
        if(this.root == null) {
            throw new Exception("The configuration tree has no root node.");
        }
        
        if(this.tree != null) {
            return this.tree;
        }
        
//        this.tree = this.root.getNode(true);
        return this.tree;
    }
}
