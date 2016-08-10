package com.marvin.component.configuration.builder.node;

import com.marvin.component.config_test.builder.NodeParentInterface;

public interface NodeInterface {
    
    String getName();
    
    String getPath();
    
    void setRequired(boolean required);
    
    boolean isRequired();
    
    boolean hasDefaultValue();
    
    NodeParentInterface getParent();
    
    void setParent(NodeParentInterface parent);
}
