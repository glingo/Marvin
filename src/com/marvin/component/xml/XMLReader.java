package com.marvin.component.xml;

import com.marvin.component.resource.ResourceService;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public abstract class XMLReader {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected DocumentLoader documentLoader;
    protected ResourceService resourceService;
    protected XMLReaderContext context;
    
    public XMLReader(ResourceService resourceService) {
        this(resourceService, new DocumentLoader());
    }
    
    public XMLReader(ResourceService resourceService, DocumentLoader documentLoader) {
        this.context = new XMLReaderContext(resourceService, this);
        this.resourceService = resourceService;
        this.documentLoader = documentLoader;
    }

    public XMLReader(XMLReaderContext context, ResourceService resourceService, DocumentLoader documentLoader) {
        this.context = context;
        this.resourceService = resourceService;
        this.documentLoader = documentLoader;
    }

    public void read(String location) {
        try (InputStream resource = this.resourceService.load(location)) {
            this.context.setParent(location.substring(0, location.lastIndexOf("/")));
            read(resource);
        } catch(Exception exception) {
            String msg = String.format("Resource %s does not exists", location);
            this.logger.log(Level.SEVERE, msg, exception);
        }
    }
    
    private void read(InputStream is) throws Exception {
        InputSource inputSource = new InputSource(is);
        doRead(inputSource);
    }

    protected abstract void doRead(InputSource inputSource) throws Exception;

    protected Document doLoadDocument(InputSource inputSource) throws Exception {
        return this.documentLoader.load(inputSource, DocumentLoader.VALIDATION_NONE, true);
    }

    public DocumentLoader getDocumentLoader() {
        return this.documentLoader;
    }

    public void setDocumentLoader(DocumentLoader documentLoader) {
        this.documentLoader = documentLoader;
    }

    public ResourceService getResourceService() {
        return this.resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
