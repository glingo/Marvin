package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.PrototypeNodeInterface;
import com.marvin.component.configuration.builder.node.ArrayNode;
import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.configuration.builder.node.PrototypedArrayNode;

public class ArrayNodeDefinition extends NodeDefinition {

    protected NodeDefinition prototype;
    
    public ArrayNodeDefinition(String name) {
        super(name);
    }
    
    public NodeDefinition prototype(String type) throws Exception{
        this.prototype = this.getNodeBuilder().node(null, type);
        this.prototype.setParent(this);
        return this.prototype;
    }
    
    @Override
    public Node createNode() {
        if(null == this.prototype) {
            ArrayNode node = new ArrayNode(this.name);
            
            if(this.children != null) {
                this.children.values().forEach((NodeDefinition definition) -> {
                    definition.setParent(this);
                    node.addChild(definition.getNode());
                });
            }
            
            return node;
        } else {
            PrototypedArrayNode node = new PrototypedArrayNode(this.name);
            
            this.prototype.setParent(this);
            node.setPrototype((PrototypeNodeInterface) this.prototype.getNode());
            
            return node;
        }
    }
    
    

}
