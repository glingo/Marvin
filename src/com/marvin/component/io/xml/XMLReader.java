/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.io.xml;

import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.DefaultResourceLoader;
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
public abstract class XMLReader {
    
    protected DocumentLoader documentLoader;
    protected ResourceLoader resourceLoader;
    protected XMLReaderContext context;
    
    public XMLReader() {
        this.context = new XMLReaderContext(this);
        this.resourceLoader = new DefaultResourceLoader();
        this.documentLoader = new DocumentLoader();
    }

    public XMLReader(ResourceLoader resourceLoader, DocumentLoader documentLoader) {
        this.context = new XMLReaderContext(this);
        this.resourceLoader = resourceLoader;
        this.documentLoader = documentLoader;
    }
    
    public XMLReader(XMLReaderContext context, ResourceLoader resourceLoader, DocumentLoader documentLoader) {
        this.context = context;
        this.resourceLoader = resourceLoader;
        this.documentLoader = documentLoader;
    }
    
    public void read(String location) {
        IResource resource = this.resourceLoader.load(location);
        read(resource);
    }
    
    public void read(IResource resource) {
        try {
            InputStream inputStream = resource.getInputStream();

            try {
                InputSource inputSource = new InputSource(inputStream);
                doRead(inputSource, resource);
            } finally {
                inputStream.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(XMLDefinitionReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected abstract void doRead(InputSource inputSource, IResource resource);
    
    protected Document doLoadDocument(InputSource inputSource, IResource resource) throws Exception {
        return this.documentLoader.load(inputSource, DocumentLoader.VALIDATION_NONE, true);
    }
    
    public DocumentLoader getDocumentLoader() {
        return documentLoader;
    }

    public void setDocumentLoader(DocumentLoader documentLoader) {
        this.documentLoader = documentLoader;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
}
