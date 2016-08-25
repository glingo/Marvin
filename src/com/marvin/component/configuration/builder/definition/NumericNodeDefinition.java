package com.marvin.component.configuration.builder.definition;

public abstract class NumericNodeDefinition extends ScalarNodeDefinition {

    protected Number max;
    protected Number min;
    
    public NumericNodeDefinition(String name) {
        super(name);
    }
    
    public abstract void max(Number max) throws Exception;
    
    public abstract void min(Number min) throws Exception;
}
