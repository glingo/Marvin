/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.extension;

import com.marvin.component.configuration.ConfigurationInterface;
import com.marvin.component.configuration.Processor;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.util.ClassUtils;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public abstract class Extension implements ExtensionInterface {
    
    @Override
    abstract public void load(HashMap<String, Object> configs, ContainerBuilder builder);
    
    @Override
    public String getAlias(){
        String name = this.getClass().getSimpleName().replace("Extension", "");
        return ContainerBuilder.underscore(name);
    }
    
    public ConfigurationInterface getConfiguration(){
        String className = String.format("%s.Configuration", this.getClass().getPackage().getName());
        
        try {
            Class cl = ClassUtils.forName(className, null);
            return (ConfigurationInterface) cl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }
    
    final protected HashMap<String, Object> processConfiguration(ConfigurationInterface configuration, HashMap<String, Object> configs) throws Exception {
        
        Processor processor = new Processor();
        
        return (HashMap<String, Object>) processor.processConfiguration(configuration, configs);
    }
}
