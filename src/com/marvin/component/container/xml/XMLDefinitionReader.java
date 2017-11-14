package com.marvin.component.container.xml;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.resource.ResourceService;
import com.marvin.component.xml.DocumentLoader;
import com.marvin.component.xml.XMLReader;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLDefinitionReader extends XMLReader {

    protected ContainerBuilder containerBuilder;
    
    public XMLDefinitionReader(ResourceService resourceService, ContainerBuilder containerBuilder) {
        this(resourceService);
        this.containerBuilder = containerBuilder;
    }
    
    public XMLDefinitionReader(ResourceService resourceService) {
        super(resourceService, new DocumentLoader());
    }
    
    @Override
    protected void doRead(InputSource inputSource) throws Exception {
        Document doc = doLoadDocument(inputSource);
        registerDefinitions(doc);
    }
    
    public void registerDefinitions(Document doc) {
        XMLDefinitionDocumentReader documentReader = new XMLDefinitionDocumentReader(this.context);
        documentReader.registerDefinitions(doc, getContainerBuilder());
    }

    public ContainerBuilder getContainerBuilder() {
        if(containerBuilder == null) {
            this.containerBuilder = new ContainerBuilder();
        }
        return containerBuilder;
    }

    public void setContainerBuilder(ContainerBuilder containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

}
