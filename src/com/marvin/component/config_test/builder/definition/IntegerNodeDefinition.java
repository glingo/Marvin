package com.marvin.component.config_test.builder.definition;

import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.NodeParentInterface;
import com.marvin.component.config_test.builder.node.IntegerNode;

public class IntegerNodeDefinition extends NumericNodeDefinition {

    public IntegerNodeDefinition(String name) {
        super(name);
    }
        
//    public IntegerNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }
    
    @Override
    protected NodeInterface instatiateNode() {
//        return new IntegerNode(this.name, this.parent);
        return new IntegerNode(this.name);
    }
    
    
    @Override
    public void max(Number max) throws Exception {
        if(this.min != null && this.min.intValue() > max.intValue()) {
            String msg = String.format("You cannot define a max(%s) as you already have a min(%s)", max, this.min);
            throw new Exception(msg);
        }
        
        this.max = max;
    }

    @Override
    public void min(Number min) throws Exception {
        if(this.max != null && this.max.intValue() < min.intValue()) {
            String msg = String.format("You cannot define a min(%s) as you already have a max(%s)", min, this.max);
            throw new Exception(msg);
        }
        
        this.min = min;
    }
    
}
