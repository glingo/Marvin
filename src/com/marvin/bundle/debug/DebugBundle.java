package com.marvin.bundle.debug;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.ClassPathResourceLoader;
import com.marvin.component.kernel.bundle.Bundle;

public class DebugBundle extends Bundle {

    @Override
    public void build(ContainerBuilder builder) {
        ClassPathResourceLoader loader = new ClassPathResourceLoader(this.getClass());
        XMLDefinitionReader reader = new XMLDefinitionReader(loader, builder);
        reader.read("resources/config/services.xml");
    }

    
}
