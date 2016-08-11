/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration;

import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.util.ObjectUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cdi305
 */
public class Processor {
    
    public void process(Node root, HashMap<String, Object> config) {
        HashMap<String, Object> current = new HashMap<>();
        
        config.forEach((String name, Object value) -> {
            // normalize
            // value = root.normalize(value);

            // merge
            // current = root.merge(current, value);
        
        });
        
        // finalize and return
        // return root.finalize(current);
    }
    
    
    public void processConfiguration(ConfigurationInterface configuration, HashMap<String, Object> config) throws Exception{
        Node root = configuration.getConfigTreeBuilder().buildTree();
        // call process
        // return this.process(root, config);
        
    }
    
    public static Object normalizeConfig(HashMap<String, Object> config, String key, String plural){
        
        if(plural == null) {
            plural = key + "s";
        }
        
        if(config.containsKey(plural)) {
            return config.get(plural);
        }
        
        if(config.containsKey(key)) {
            Object value = config.get(key);
            
            if(!ObjectUtils.isArray(value) 
                    || !(value instanceof Collection) 
                    || !(value instanceof Map)) {
                
                return ObjectUtils.toObjectArray(value);
            }
            
            return config.get(key);
        }
        
        return new Object[]{};
    }
}
