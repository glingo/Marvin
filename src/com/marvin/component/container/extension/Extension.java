package com.marvin.component.container.extension;

import com.marvin.component.configuration.ConfigurationInterface;
import com.marvin.component.configuration.Processor;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.util.ClassUtils;
import java.util.Map;
import java.util.logging.Logger;

public abstract class Extension implements ExtensionInterface {
    
    private static final String CONFIGURATION_PATH = "%s.Configuration";
    private static final String EXTENSION_SUFFIX   = "Extension";
    
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    @Override
    abstract public void load(Map<String, Object> configs, ContainerBuilder builder);
    
    @Override
    public String getAlias(){
        String name = getClass().getSimpleName().replace(EXTENSION_SUFFIX, "");
        return ContainerBuilder.underscore(name);
    }
    
    private String getConfigurationPath(){
        String pckgName = ClassUtils.getPackageName(getClass());
        return String.format(CONFIGURATION_PATH, pckgName);
    }
    
    public ConfigurationInterface getConfiguration(){
        try {
            Class cl = ClassUtils.forName(getConfigurationPath(), null);
            return (ConfigurationInterface) cl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }
    
    protected final Map<String, Object> processConfiguration(ConfigurationInterface configuration, Map<String, Object> configs) {
        Processor processor = new Processor();
        return (Map<String, Object>) processor.processConfiguration(configuration, configs);
    }
    
}
