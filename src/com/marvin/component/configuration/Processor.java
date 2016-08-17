package com.marvin.component.configuration;

import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.util.ObjectUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Processor {
    
    public HashMap<String, Object> process(Node root, HashMap<String, Object> config) throws Exception {
        Object current = new HashMap<>();
        
        if(config == null) {
            return null;
        }
        
        for (Map.Entry<String, Object> entry : config.entrySet()) {
//            String key = entry.getKey();
            Object value = entry.getValue();
            // normalize
            value = root.normalize(value);
            // merge
            current = root.merge(current, value);
        }
        
        // finalize and return
         return (HashMap<String, Object>) root.finalize(current);
    }
    
    
    public Object processConfiguration(ConfigurationInterface configuration, HashMap<String, Object> config) throws Exception{
        Node root = configuration.getConfigTreeBuilder().buildTree();
        return this.process(root, config);
        
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
