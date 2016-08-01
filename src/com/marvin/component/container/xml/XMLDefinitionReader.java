/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.xml;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.io.resource.IResource;
import com.marvin.component.io.xml.DocumentLoader;
import com.marvin.component.io.xml.XMLReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author cdi305
 */
public class XMLDefinitionReader extends XMLReader {

    protected ContainerBuilder containerBuilder;

    public XMLDefinitionReader(ContainerBuilder containerBuilder) {
        super();
        this.containerBuilder = containerBuilder;
    }
    
    public XMLDefinitionReader(ContainerBuilder containerBuilder, ResourceLoader loader) {
        super(loader, new DocumentLoader());
        this.containerBuilder = containerBuilder;
    }

    @Override
    protected void doRead(InputSource inputSource, IResource resource) {
        try {
            Document doc = doLoadDocument(inputSource, resource);
            registerDefinitions(doc, resource);
        } catch (Exception ex) {
            Logger.getLogger(XMLDefinitionReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerDefinitions(Document doc, IResource resource) {
        XMLDefinitionDocumentReader documentReader = new XMLDefinitionDocumentReader(this.context);
        documentReader.registerDefinitions(doc, containerBuilder);
    }

    public ContainerBuilder getContainerBuilder() {
        return containerBuilder;
    }

    public void setContainerBuilder(ContainerBuilder containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

}
