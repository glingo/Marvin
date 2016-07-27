/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.xml;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.io.resource.IResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author cdi305
 */
public class XMLDefinitionReader {

    protected ResourceLoader resourceLoader;
    protected ContainerBuilder containerBuilder;
    
    protected DocumentLoader documentLoader = new DocumentLoader();

    public XMLDefinitionReader(ContainerBuilder containerBuilder, ResourceLoader resourceLoader) {
        this.containerBuilder = containerBuilder;
        this.resourceLoader = resourceLoader;
    }

    public void loadDefinitions(String location) {
        IResource resource = this.resourceLoader.load(location);
        loadDefinitions(resource);
    }

    public void loadDefinitions(IResource resource) {
        try {
            InputStream inputStream = resource.getInputStream();

            try {
                InputSource inputSource = new InputSource(inputStream);
                doLoadDefinitions(inputSource, resource);
            } finally {
                inputStream.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(XMLDefinitionReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void doLoadDefinitions(InputSource inputSource, IResource resource) {
        try {
            Document doc = doLoadDocument(inputSource, resource);
            registerDefinitions(doc, resource);
        } catch (Exception ex) {
            Logger.getLogger(XMLDefinitionReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected Document doLoadDocument(InputSource inputSource, IResource resource) throws Exception {
        return this.documentLoader.load(inputSource, DocumentLoader.VALIDATION_NONE, true);
    }
    
    public void registerDefinitions(Document doc, IResource resource) {
        XMLDefinitionDocumentReader documentReader = new XMLDefinitionDocumentReader();
        documentReader.registerBeanDefinitions(doc, containerBuilder);
        
//        System.out.println(containerBuilder.getDefinitions());
//        documentReader.
//        int countBefore = getRegistry().getBeanDefinitionCount();
//        documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
//        return getRegistry().getBeanDefinitionCount() - countBefore;
    }

}
