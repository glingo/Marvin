package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeParentInterface;
import com.marvin.component.configuration.builder.NodeBuilder;
import com.marvin.component.configuration.builder.node.ArrayNode;
import com.marvin.component.configuration.builder.node.NodeInterface;
import com.marvin.component.configuration.builder.node.PrototypeNodeInterface;
import com.marvin.component.configuration.builder.node.PrototypedArrayNode;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author cdi305
 */
public class ArrayNodeDefinition extends NodeDefinition {

    protected ConcurrentHashMap<String, NodeDefinition> children = new ConcurrentHashMap<>();
    protected NodeDefinition prototype;
    
    public ArrayNodeDefinition(String name) {
        super(name);
    }

    public ArrayNodeDefinition(String name, NodeParentInterface parent) {
        super(name, parent);
    }
    
    protected NodeBuilder getNodeBuilder(){
        if(this.builder == null) {
            this.builder = new NodeBuilder();
        }
        
        return this.builder;
    }

//    @Override
    public NodeBuilder children() {
        return this.getNodeBuilder();
    }
    
    public NodeDefinition prototype(String type) throws Exception{
        this.prototype = this.getNodeBuilder().node(null, type);
        this.prototype.setParent(this);
        return this.prototype;
    }
    
    @Override
    public NodeInterface createNode() {
//        ArrayNode node;
        if(null == this.prototype) {
            ArrayNode node = new ArrayNode(getName(), getParent());
            
            this.children.values().forEach((NodeDefinition definition) -> {
//                definition.setParent(this);
                node.addChild(definition.getNode());
            });
            
            return node;
        } else {
            PrototypedArrayNode node = new PrototypedArrayNode(getName(), getParent());
            
//            this.prototype.setParent(this);
            node.setPrototype((PrototypeNodeInterface) this.prototype.getNode());
            
            return node;
        }
    }

//    @Override
//    public NodeParentInterface append(NodeDefinition definition) throws CloneNotSupportedException {
//        definition.setParent(this);
//        this.children.putIfAbsent(definition.getName(), definition);
//        return this;
//    }

}
