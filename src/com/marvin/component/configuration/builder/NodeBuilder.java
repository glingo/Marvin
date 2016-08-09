/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.definition.ArrayNodeDefinition;
import com.marvin.component.configuration.definition.BooleanNodeDefinition;
import com.marvin.component.configuration.definition.EnumNodeDefinition;
import com.marvin.component.configuration.definition.FloatNodeDefinition;
import com.marvin.component.configuration.definition.IntegerNodeDefinition;
import com.marvin.component.configuration.definition.NodeDefinition;
import com.marvin.component.configuration.definition.ScalarNodeDefinition;
import com.marvin.component.configuration.definition.VariableNodeDefinition;
import com.marvin.component.configuration.node.NodeParentInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class NodeBuilder implements NodeParentInterface {
    
    protected NodeParentInterface parent;
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
    
    public NodeDefinition node(String name, String type) {
        NodeDefinition node = null;
        try {
            Class<NodeDefinition> cl = this.definitionMapping.getOrDefault(type, null);
            Constructor<NodeDefinition> ctr = cl.getConstructor(new Class[]{String.class});
            node = ctr.newInstance(name);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(NodeBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return node;
    }
    
    public NodeDefinition arrayNode(String name) {
        return this.node(name, "array");
    }

    public void setParent(NodeParentInterface parent) {
        this.parent = parent;
    }
    
    public NodeParentInterface end(){
        return this.parent;
    }
    
    @Override
    public NodeParentInterface append(NodeDefinition definition) {

        try {
            definition.setBuilder((NodeBuilder) this.clone());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(NodeBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(this.parent != null) {
            this.parent.append(definition);
//            definition.setParent(this);
        }
        
        return this;
    }

}
