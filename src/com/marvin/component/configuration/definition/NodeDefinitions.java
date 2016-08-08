package com.marvin.component.configuration.definition;

/**
 *
 * @author cdi305
 */
public enum NodeDefinitions {

    VARIABLE("variable", VariableNodeDefinition.class),
    SCALAR("scalar", ScalarNodeDefinition.class),
    BOOLEAN("boolean", BooleanNodeDefinition.class),
    INTEGER("integer", IntegerNodeDefinition.class),
    FLOAT("float", FloatNodeDefinition.class),
    ARRAY("array", ArrayNodeDefinition.class),
    ENUM("enum", EnumNodeDefinition.class);

    private final Class definitionClass;
    private final String nickName;

    private NodeDefinitions(String nickName, Class definitionClass) {
        this.definitionClass = definitionClass;
        this.nickName = nickName;
    }

    public Class getDefinitionClass() {
        return this.definitionClass;
    }

    public String getNickName() {
        return nickName;
    }

    public static Class getDefinitionClass(String nickName) throws Exception {
        
        if (VARIABLE.getNickName().equals(nickName)) {
            return VARIABLE.getDefinitionClass();
        } else if (SCALAR.getNickName().equals(nickName)) {
            return SCALAR.getDefinitionClass();
        } else if (BOOLEAN.getNickName().equals(nickName)) {
            return BOOLEAN.getDefinitionClass();
        } else if (INTEGER.getNickName().equals(nickName)) {
            return INTEGER.getDefinitionClass();
        } else if (FLOAT.getNickName().equals(nickName)) {
            return FLOAT.getDefinitionClass();
        } else if (ARRAY.getNickName().equals(nickName)) {
            return ARRAY.getDefinitionClass();
        } else if (ENUM.getNickName().equals(nickName)) {
            return ENUM.getDefinitionClass();
        }
        
        throw new Exception(String.format("Class definition %s does not exists", nickName));
    }

}
