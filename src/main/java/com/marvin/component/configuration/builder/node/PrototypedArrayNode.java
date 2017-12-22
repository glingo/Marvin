package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.PrototypeNodeInterface;

public class PrototypedArrayNode extends ArrayNode {

    protected PrototypeNodeInterface prototype;
    
    public PrototypedArrayNode(String name) {
        super(name);
    }

    public PrototypeNodeInterface getPrototype() {
        return this.prototype;
    }

    public void setPrototype(PrototypeNodeInterface prototype) {
        this.prototype = prototype;
    }
    
}
