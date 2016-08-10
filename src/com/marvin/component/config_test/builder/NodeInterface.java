package com.marvin.component.config_test.builder;

public interface NodeInterface {
    
    String getName();
    
    String getPath();
    
    void setRequired(boolean required);
    
    boolean isRequired();
    
    boolean hasDefaultValue();
    
    NodeParentInterface getParent();
    
    void setParent(NodeParentInterface parent);
}
