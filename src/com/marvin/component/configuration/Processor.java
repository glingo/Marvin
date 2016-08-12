package com.marvin.component.configuration;

import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.util.ObjectUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Processor {
    
    public Object process(Node root, HashMap<String, Object> config) throws Exception {
        HashMap<String, Object> current = new HashMap<>();
        
        config.values().stream().map((Object value) -> {
            
            // normalize
            value = root.normalize(value);
            try {
                // merge
                return root.merge(current, value);
            } catch (Exception ex) {
                Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return value;
        
        });
        
        // finalize and return
         return root.finalize(current);
    }
    
    
    public Object processConfiguration(ConfigurationInterface configuration, HashMap<String, Object> config) throws Exception{
        Node root = configuration.getConfigTreeBuilder().buildTree();
        // call process
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
