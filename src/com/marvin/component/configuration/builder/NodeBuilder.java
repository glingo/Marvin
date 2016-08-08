/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.builder;

import com.marvin.component.configuration.definition.ArrayNodeDefinition;
import com.marvin.component.configuration.definition.NodeDefinition;
import com.marvin.component.configuration.definition.NodeDefinitions;
import com.marvin.component.configuration.definition.ParentNodeDefinitionInterface;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class NodeBuilder {
    
    protected ParentNodeDefinitionInterface parent;
    
    public NodeDefinition node(String name, String type) throws Exception{
        Class<NodeDefinition> cl = NodeDefinitions.getDefinitionClass(type);
        Constructor<NodeDefinition> ctr = cl.getConstructor(new Class[]{String.class});
        return ctr.newInstance(name);
    }
    
    public ArrayNodeDefinition arrayNode(String name) {
        try {
            return (ArrayNodeDefinition) this.node(name, "array");
        } catch (Exception ex) {
            Logger.getLogger(NodeBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setParent(ParentNodeDefinitionInterface parent) {
        this.parent = parent;
    }
    
    public ParentNodeDefinitionInterface end(){
        return this.parent;
    }
    
    public NodeBuilder append(NodeDefinition definition) {

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
