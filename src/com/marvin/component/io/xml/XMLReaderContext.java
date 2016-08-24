package com.marvin.component.io.xml;

public class XMLReaderContext {
    
    protected XMLReader reader;

    public XMLReaderContext(XMLReader reader) {
        this.reader = reader;
    }

    public XMLReader getReader() {
        return reader;
    }

    public void setReader(XMLReader reader) {
        this.reader = reader;
    }

}
