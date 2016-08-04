package com.marvin.bundle.framework;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.ClassPathResourceLoader;
import com.marvin.component.kernel.bundle.Bundle;

public class FrameWorkBundle extends Bundle {

    @Override
    public void build(ContainerBuilder builder) {
        ClassPathResourceLoader loader = new ClassPathResourceLoader(this.getClass());
        XMLDefinitionReader reader = new XMLDefinitionReader(builder, loader);
        reader.read("resources/config/services.xml");
    }

    
}
