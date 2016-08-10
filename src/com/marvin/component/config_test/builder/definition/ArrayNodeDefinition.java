package com.marvin.component.config_test.builder.definition;

import com.marvin.component.config_test.builder.NodeBuilder;
import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.NodeParentInterface;
import com.marvin.component.config_test.builder.ParentNodeDefinitionInterface;
import com.marvin.component.config_test.builder.PrototypeNodeInterface;
import com.marvin.component.config_test.builder.node.ArrayNode;
import com.marvin.component.config_test.builder.node.PrototypedArrayNode;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author cdi305
 */
public class ArrayNodeDefinition extends NodeDefinition implements ParentNodeDefinitionInterface {

    protected NodeDefinition prototype;
    protected NodeBuilder nodeBuilder;
    protected ConcurrentHashMap<String, NodeDefinition> children = new ConcurrentHashMap<>();
    
    public ArrayNodeDefinition(String name) {
        super(name);
    }

//    public ArrayNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }
    
    protected NodeBuilder getNodeBuilder(){
        if(this.nodeBuilder == null) {
            this.nodeBuilder = new NodeBuilder();
        }
        
        return this.nodeBuilder;
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
//            ArrayNode node = new ArrayNode(this.name, this.parent);
            ArrayNode node = new ArrayNode(this.name);
            
            this.children.values().forEach((NodeDefinition definition) -> {
                definition.setParent(this);
                node.addChild(definition.getNode());
            });
            
            return node;
        } else {
//            PrototypedArrayNode node = new PrototypedArrayNode(this.name, this.parent);
            PrototypedArrayNode node = new PrototypedArrayNode(this.name);
            
            this.prototype.setParent(this);
            node.setPrototype((PrototypeNodeInterface) this.prototype.getNode());
            
            return node;
        }
    }

//    @Override
    public ParentNodeDefinitionInterface append(NodeDefinition definition) throws CloneNotSupportedException {
        this.children.putIfAbsent(definition.name, definition);
        definition.setParent(this);
        return this;
    }

}
