package com.marvin.component.io.xml;

import com.marvin.component.io.loader.ResourceLoader;

public class XMLReaderContext {
    
    protected String parent;
    
    protected ResourceLoader loader;
    protected XMLReader reader;

    public XMLReaderContext(XMLReader reader) {
        this.reader = reader;
    }
    
    public XMLReaderContext(ResourceLoader loader, XMLReader reader) {
        this.loader = loader;
        this.reader = reader;
    }

    public ResourceLoader getLoader() {
        return loader;
    }

    public void setLoader(ResourceLoader loader) {
        this.loader = loader;
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
