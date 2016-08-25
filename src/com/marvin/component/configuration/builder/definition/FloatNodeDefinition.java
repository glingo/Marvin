package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.node.FloatNode;
import com.marvin.component.configuration.builder.node.Node;

public class FloatNodeDefinition extends NumericNodeDefinition {

    public FloatNodeDefinition(String name) {
        super(name);
    }
    
    @Override
    protected Node instatiateNode() {
        return new FloatNode(this.name);
    }

    @Override
    public void max(Number max) throws Exception {
        if(this.min != null && this.min.floatValue() > max.floatValue()) {
            String msg = String.format("You cannot define a max(%s) as you already have a min(%s)", max, this.min);
            throw new Exception(msg);
        }
        
        this.max = max;
    }

    @Override
    public void min(Number min) throws Exception {
        if(this.max != null && this.max.floatValue() < min.floatValue()) {
            String msg = String.format("You cannot define a min(%s) as you already have a max(%s)", min, this.max);
            throw new Exception(msg);
        }
        
        this.min = min;
    }
    
}
