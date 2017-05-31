package com.marvin.component.io.xml;

import com.marvin.component.io.loader.DefaultResourceLoader;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.io.IResource;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public abstract class XMLReader {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected DocumentLoader documentLoader;
    protected ResourceLoader resourceLoader;
    protected XMLReaderContext context;

    public XMLReader() {
        this(new DefaultResourceLoader(), new DocumentLoader());
    }

    public XMLReader(ResourceLoader resourceLoader, DocumentLoader documentLoader) {
        this.context = new XMLReaderContext(resourceLoader, this);
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

        if (!resource.exists()) {
            String msg = String.format("Resource %s does not exists, %s\n", location, this.resourceLoader);
            this.logger.log(Level.SEVERE, msg);
            return;
        }
        
        this.context.setParent(location.substring(0, location.lastIndexOf("/")));
        read(resource);
    }
    
    private void read(IResource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            InputSource inputSource = new InputSource(inputStream);
            doRead(inputSource, resource);
        } catch (Exception ex) {
            String msg = String.format("Unable to read resource %s, %s\n", resource, this.resourceLoader);
            this.logger.log(Level.SEVERE, msg);
        }
    }

    protected abstract void doRead(InputSource inputSource, IResource resource) throws Exception;

    protected Document doLoadDocument(InputSource inputSource, IResource resource) throws Exception {
        return this.documentLoader.load(inputSource, DocumentLoader.VALIDATION_NONE, true);
    }

    public DocumentLoader getDocumentLoader() {
        return this.documentLoader;
    }

    public void setDocumentLoader(DocumentLoader documentLoader) {
        this.documentLoader = documentLoader;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
