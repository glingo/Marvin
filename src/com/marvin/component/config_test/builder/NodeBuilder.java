/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.config_test.builder;

import com.marvin.component.config_test.builder.definition.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public class NodeBuilder {
    
    protected HashMap<String, Class> definitionMapping;

    public NodeBuilder() {
        HashMap<String, Class> definitions = new HashMap();
        definitions.put("variable", VariableNodeDefinition.class);
        definitions.put("scalar", ScalarNodeDefinition.class);
        definitions.put("boolean", BooleanNodeDefinition.class);
        definitions.put("integer", IntegerNodeDefinition.class);
        definitions.put("float", FloatNodeDefinition.class);
        definitions.put("array", ArrayNodeDefinition.class);
        definitions.put("enum", EnumNodeDefinition.class);
        this.definitionMapping = definitions;
    }
    
    public NodeBuilder append(NodeDefinition definition) throws CloneNotSupportedException {

        if(definition instanceof ParentNodeDefinitionInterface) {
            ParentNodeDefinitionInterface def = (ParentNodeDefinitionInterface) definition;
            NodeBuilder builder = (NodeBuilder) this.clone();
            def.setBuilder(builder);
        }

        if(this.parent != null) {
            this.parent.append(definition);
            definition.setParent(this);
        }
        
        return this;
    }
    
    public NodeDefinition node(String name, String type) throws Exception {
        Class<NodeDefinition> cl = this.definitionMapping.getOrDefault(type, null);
        Constructor<NodeDefinition> ctr = cl.getConstructor(new Class[]{String.class});
        NodeDefinition node = ctr.newInstance(name);
        this.append(node);
        return node;
    }
    
    public NodeDefinition variableNode(String name) throws Exception {
        return this.node(name, "variable");
    }
     
    public NodeDefinition enumNode(String name) throws Exception {
        return this.node(name, "enum");
    }
    
    public NodeDefinition floatNode(String name) throws Exception {
        return this.node(name, "float");
    }
    
    public NodeDefinition integerNode(String name) throws Exception {
        return this.node(name, "integer");
    }
    
    public NodeDefinition booleanNode(String name) throws Exception {
        return this.node(name, "boolean");
    }
    
    public NodeDefinition scalarNode(String name) throws Exception {
        return this.node(name, "scalar");
    }
    
    public ParentNodeDefinitionInterface arrayNode(String name) throws Exception {
        return (ParentNodeDefinitionInterface) this.node(name, "array");
    }
}
