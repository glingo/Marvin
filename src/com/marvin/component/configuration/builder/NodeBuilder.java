package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.builder.definition.ArrayNodeDefinition;
import com.marvin.component.configuration.builder.definition.BooleanNodeDefinition;
import com.marvin.component.configuration.builder.definition.EnumNodeDefinition;
import com.marvin.component.configuration.builder.definition.FloatNodeDefinition;
import com.marvin.component.configuration.builder.definition.IntegerNodeDefinition;
import com.marvin.component.configuration.builder.definition.NodeDefinition;
import com.marvin.component.configuration.builder.definition.ScalarNodeDefinition;
import com.marvin.component.configuration.builder.definition.VariableNodeDefinition;
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
    
    public NodeDefinition node(String name, String type) {
        try {
            Class<NodeDefinition> cl = this.definitionMapping.getOrDefault(type, null);
            Constructor<NodeDefinition> ctr = cl.getConstructor(new Class[]{String.class});
            NodeDefinition node = ctr.newInstance(name);
            
            if(node instanceof NodeParentDefinitionInterface) {
                NodeBuilder builder = (NodeBuilder) clone();
                ((NodeParentDefinitionInterface) node).setBuilder(builder);
            }
            
            if(this.parent != null) {
                this.parent.append(node);
            }
            
            return node;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public VariableNodeDefinition variableNode(String name) {
        return (VariableNodeDefinition) node(name, "variable");
    }
     
    public EnumNodeDefinition enumNode(String name) {
        return (EnumNodeDefinition) node(name, "enum");
    }
    
    public FloatNodeDefinition floatNode(String name) {
        return (FloatNodeDefinition) node(name, "float");
    }
    
    public IntegerNodeDefinition integerNode(String name) {
        return (IntegerNodeDefinition) node(name, "integer");
    }
    
    public BooleanNodeDefinition booleanNode(String name) {
        return (BooleanNodeDefinition) node(name, "boolean");
    }
    
    public ScalarNodeDefinition scalarNode(String name) {
        return (ScalarNodeDefinition) node(name, "scalar");
    }
    
    public ArrayNodeDefinition arrayNode(String name) {
        return (ArrayNodeDefinition) node(name, "array");
    }
}
