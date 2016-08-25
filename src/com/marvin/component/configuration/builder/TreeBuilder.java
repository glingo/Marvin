package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.builder.definition.NodeDefinition;
import com.marvin.component.configuration.builder.node.Node;

public class TreeBuilder {
    
    protected Node tree;
    protected NodeDefinition root;
    protected NodeBuilder builder;
    
    public NodeDefinition root(String name) throws Exception {
        return root(name, "array", null);
    }
    
    public NodeDefinition root(String name, String type, NodeBuilder builder) throws Exception {
        
        if(builder == null) {
            builder = new NodeBuilder();
        }
        
        setBuilder(builder);
        
        setRoot(getBuilder().node(name, type));
        getBuilder().setParent(root);
        
        return getRoot();
    }
    
    public Node buildTree() throws Exception {
        
        if(getRoot() == null) {
            throw new Exception("The configuration tree has no root node.");
        }
        
        if(getTree() != null) {
            return getTree();
        }
        
        setTree(getRoot().getNode(true));
        
        return getTree();
    }

    public NodeDefinition getRoot() {
        return this.root;
    }

    public Node getTree() {
        return this.tree;
    }

    public NodeBuilder getBuilder() {
        return this.builder;
    }

    public void setBuilder(NodeBuilder builder) {
        this.builder = builder;
    }

    public void setRoot(NodeDefinition root) {
        this.root = root;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TreeBuilder : \n");
        sb.append(this.root);
        return sb.toString();
    }
}
