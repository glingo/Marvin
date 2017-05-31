package com.marvin.bundle.menu.container;

import com.marvin.bundle.console.ConsoleBundle;
import com.marvin.component.configuration.ConfigurationInterface;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.extension.Extension;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.ClassPathResourceLoader;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.io.xml.XMLReader;

import java.util.Map;

public class MenuExtension extends Extension {

    @Override
    public void load(Map<String, Object> configs, ContainerBuilder builder) {
        ResourceLoader loader = new ClassPathResourceLoader(ConsoleBundle.class);
        XMLReader reader = new XMLDefinitionReader(loader, builder);

        reader.read("resources/config/services.xml");
        
        ConfigurationInterface configuration = getConfiguration();
        Map<String, Object> config = processConfiguration(configuration, configs);

    }
}
