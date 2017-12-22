package com.marvin.bundle.debug;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.kernel.bundle.Bundle;
import com.marvin.component.resource.ResourceService;
import com.marvin.component.resource.loader.ClasspathResourceLoader;
import com.marvin.component.resource.reference.ResourceReference;
import com.marvin.component.xml.XMLReader;

public class DebugBundle extends Bundle {

    @Override
    public void build(ContainerBuilder builder) {
        ResourceService service = ResourceService.builder()
                .with(ResourceReference.CLASSPATH, new ClasspathResourceLoader(DebugBundle.class))
                .build();
        XMLReader reader = new XMLDefinitionReader(service, builder);
        reader.read("resources/config/services.xml");
    }
}
