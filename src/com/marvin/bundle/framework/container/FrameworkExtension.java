package com.marvin.bundle.framework.container;

import com.marvin.component.configuration.ConfigurationInterface;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.extension.Extension;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.ClassPathResourceLoader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameworkExtension extends Extension {

    @Override
    public void load(HashMap<String, Object> configs, ContainerBuilder builder) {
        try {
            ClassPathResourceLoader loader = new ClassPathResourceLoader(this.getClass());
            XMLDefinitionReader reader = new XMLDefinitionReader(loader, builder);
            
            reader.read("../resources/config/services.xml");
            reader.read("../resources/config/templating.xml");
            reader.read("../resources/config/routing.xml");
            reader.read("../resources/config/web.xml");
            reader.read("../resources/config/shell.xml");
            reader.read("../resources/config/server.xml");
        
            ConfigurationInterface configuration = this.getConfiguration();
            HashMap<String, Object> conf = this.processConfiguration(configuration, configs);
            
//            HashMap<String, Definition> taggedDefinitions = builder.findTaggedDefinitions("event_subscriber");
            
//            for (Map.Entry<String, Definition> entrySet : taggedDefinitions.entrySet()) {
//                String id = entrySet.getKey();
//                Definition definition = entrySet.getValue();
//                
//            }
            
        } catch (Exception ex) {
            Logger.getLogger(FrameworkExtension.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
