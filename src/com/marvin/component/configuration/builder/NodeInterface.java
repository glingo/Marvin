package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.builder.node.Node;

public interface NodeInterface {
    
    String getName();
    
    String getPath();
    
    void setRequired(boolean required);
    
    boolean isRequired();
    
    boolean hasDefaultValue();
    
    Node getParent();
    
    void setParent(NodeParentInterface parent);
}
