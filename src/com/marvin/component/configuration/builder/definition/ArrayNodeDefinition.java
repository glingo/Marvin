package com.marvin.component.configuration.builder.definition;

import com.marvin.component.configuration.builder.NodeInterface;
import com.marvin.component.configuration.builder.PrototypeNodeInterface;
import com.marvin.component.configuration.builder.node.ArrayNode;
import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.configuration.builder.node.PrototypedArrayNode;

/**
 *
 * @author cdi305
 */
public class ArrayNodeDefinition extends NodeDefinition {

    protected NodeDefinition prototype;
    
    public ArrayNodeDefinition(String name) {
        super(name);
    }

//    public ArrayNodeDefinition(String name, NodeParentInterface parent) {
//        super(name, parent);
//    }
    

//    @Override
//    public NodeBuilder children() {
//        return this.getNodeBuilder();
//    }

    
    public NodeDefinition prototype(String type) throws Exception{
        this.prototype = this.getNodeBuilder().node(null, type);
        this.prototype.setParent(this);
        return this.prototype;
    }
    
    @Override
    public Node createNode() {
//        ArrayNode node;
        if(null == this.prototype) {
//            ArrayNode node = new ArrayNode(this.name, this.parent);
            ArrayNode node = new ArrayNode(this.name);
            
            if(this.children != null) {
                this.children.values().forEach((NodeDefinition definition) -> {
                    definition.setParent(this);
                    node.addChild(definition.getNode());
                });
            }
            
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
//    public NodeDefinition append(NodeDefinition definition) throws CloneNotSupportedException {
//        this.children.putIfAbsent(definition.name, definition);
//        definition.setParent(this);
//        return this;
//    }

//    @Override
//    public void setBuilder(NodeBuilder builder) {
//        this.nodeBuilder = builder;
//    }

//    @Override
//    public VariableNodeDefinition variableNode(String name) throws Exception {
//        return this.nodeBuilder.variableNode(name);
//    }
//
//    @Override
//    public EnumNodeDefinition enumNode(String name) throws Exception {
//        return this.nodeBuilder.enumNode(name);
//    }
//
//    @Override
//    public FloatNodeDefinition floatNode(String name) throws Exception {
//        return this.nodeBuilder.floatNode(name);
//    }
//
//    @Override
//    public IntegerNodeDefinition integerNode(String name) throws Exception {
//        return this.nodeBuilder.integerNode(name);
//    }
//
//    @Override
//    public BooleanNodeDefinition booleanNode(String name) throws Exception {
//        return this.nodeBuilder.booleanNode(name);
//    }
//
//    @Override
//    public ScalarNodeDefinition scalarNode(String name) throws Exception {
//        return this.nodeBuilder.scalarNode(name);
//    }
//
//    @Override
//    public ArrayNodeDefinition arrayNode(String name) throws Exception {
//        return this.nodeBuilder.arrayNode(name);
//    }

}
