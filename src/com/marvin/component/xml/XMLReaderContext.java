package com.marvin.component.xml;

import com.marvin.component.resource.ResourceService;

public class XMLReaderContext {
    
    protected String parent;
    
    protected ResourceService resourceService;
    protected XMLReader reader;

    public XMLReaderContext(XMLReader reader) {
        this.reader = reader;
    }
    
    public XMLReaderContext(ResourceService resourceService, XMLReader reader) {
        this.resourceService = resourceService;
        this.reader = reader;
    }

    public ResourceService getLoader() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
    
    public XMLReader getReader() {
        return reader;
    }

    public void setReader(XMLReader reader) {
        this.reader = reader;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
    
}
