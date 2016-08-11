package com.marvin.component.configuration.builder;

//import com.marvin.component.configuration.builder.definition.ArrayNodeDefinition;
//import com.marvin.component.configuration.builder.definition.BooleanNodeDefinition;
//import com.marvin.component.configuration.builder.definition.EnumNodeDefinition;
//import com.marvin.component.configuration.builder.definition.FloatNodeDefinition;
//import com.marvin.component.configuration.builder.definition.IntegerNodeDefinition;
//import com.marvin.component.configuration.builder.definition.ScalarNodeDefinition;
//import com.marvin.component.configuration.builder.definition.VariableNodeDefinition;

public interface NodeParentDefinitionInterface extends NodeDefinitionInterface {
    
    NodeBuilder children();
    
//    NodeParentDefinitionInterface end();
    
    void setBuilder(NodeBuilder builder);
    
//    public VariableNodeDefinition variableNode(String name) throws Exception;
//     
//    public EnumNodeDefinition enumNode(String name) throws Exception;
//    
//    public FloatNodeDefinition floatNode(String name) throws Exception;
//    
//    public IntegerNodeDefinition integerNode(String name) throws Exception;
//    
//    public BooleanNodeDefinition booleanNode(String name) throws Exception;
//    
//    public ScalarNodeDefinition scalarNode(String name) throws Exception;
//    
//    public ArrayNodeDefinition arrayNode(String name) throws Exception;
}
