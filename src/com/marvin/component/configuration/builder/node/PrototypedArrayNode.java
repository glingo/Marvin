package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.PrototypeNodeInterface;

public class PrototypedArrayNode extends ArrayNode {

    protected PrototypeNodeInterface prototype;
    
    public PrototypedArrayNode(String name) {
        super(name);
    }

//    public PrototypedArrayNode(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }

    public PrototypeNodeInterface getPrototype() {
        return prototype;
    }

    public void setPrototype(PrototypeNodeInterface prototype) {
        this.prototype = prototype;
    }
    
}
