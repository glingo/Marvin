package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.builder.definition.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class NodeBuilder {
    
    protected HashMap<String, Class> definitionMapping;
    protected NodeDefinition parent;

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

    public void setParent(NodeDefinition parent) {
        this.parent = parent;
    }
    
    public NodeDefinition node(String name, String type) throws Exception {
        Class<NodeDefinition> cl = this.definitionMapping.getOrDefault(type, null);
        Constructor<NodeDefinition> ctr = cl.getConstructor(new Class[]{String.class});
        NodeDefinition node = ctr.newInstance(name);
        
        if(node instanceof NodeParentDefinitionInterface) {
            NodeBuilder builder = (NodeBuilder) this.clone();
            ((NodeParentDefinitionInterface) node).setBuilder(builder);
        }
        
        if(this.parent != null) {
            parent.append(node);
        }
        
        return node;
    }
    
    public VariableNodeDefinition variableNode(String name) throws Exception {
        return (VariableNodeDefinition) this.node(name, "variable");
    }
     
    public EnumNodeDefinition enumNode(String name) throws Exception {
        return (EnumNodeDefinition) this.node(name, "enum");
    }
    
    public FloatNodeDefinition floatNode(String name) throws Exception {
        return (FloatNodeDefinition) this.node(name, "float");
    }
    
    public IntegerNodeDefinition integerNode(String name) throws Exception {
        return (IntegerNodeDefinition) this.node(name, "integer");
    }
    
    public BooleanNodeDefinition booleanNode(String name) throws Exception {
        return (BooleanNodeDefinition) this.node(name, "boolean");
    }
    
    public ScalarNodeDefinition scalarNode(String name) throws Exception {
        return (ScalarNodeDefinition) this.node(name, "scalar");
    }
    
    public ArrayNodeDefinition arrayNode(String name) throws Exception {
        return (ArrayNodeDefinition) this.node(name, "array");
    }
}
