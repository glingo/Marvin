package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.node.IntegerNode;
import com.marvin.component.configuration.builder.node.NodeInterface;

/**
 *
 * @author cdi305
 */
public class IntegerNodeDefinition extends NumericNodeDefinition {

    public IntegerNodeDefinition(String name) {
        super(name);
    }
        
    public IntegerNodeDefinition(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
    @Override
    protected NodeInterface instatiateNode() {
        return new IntegerNode(getName(), getParent());
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
