package com.marvin.component.configuration;

import com.marvin.component.configuration.builder.node.Node;
import com.marvin.component.util.ObjectUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Processor {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    public HashMap<String, Object> process(Node root, Map<String, Object> config) {
        this.logger.info(String.format("processing %s %s", root, config));

        if(config == null) {
            return null;
        }
        
        // ca devrait faire l'affaire quand même ! sinon check XMLDefinitionDocumentReader#convertElementToMap
        Object current = root.normalize(config);       // normalize
        
        // finalize and return
         return (HashMap<String, Object>) root.finalize(current);
         
        // On a un décallage par exemple :
        
        //  framework:
        //      router:
        //          resource: config/routing.xml
//         lors du normalize le getChildren() renvois router alors qu'il souhaite normaliser resource.
        
         
//        Object current = new HashMap<>();
        
//        if(config == null) {
//            return null;
//        }
        
//        for (Map.Entry<String, Object> entry : config.entrySet()) {
//            Object value = entry.getValue();
//            this.logger.info(String.format("value %s", value));
//            value = root.normalize(value);       // normalize
//            this.logger.info(String.format("value %s", value));
//            current = root.merge(current, value);// merge
//            this.logger.info(String.format("current %s", value));
//        }
        
//         finalize and return
//         return (HashMap<String, Object>) root.finalize(current);
    }
    
    public Object processConfiguration(ConfigurationInterface configuration, Map<String, Object> config) {
        Node root = configuration.getConfigTreeBuilder().buildTree();
        return process(root, config);
    }
    
    public static Object normalizeConfig(Map<String, Object> config, String key, String plural){
        
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
