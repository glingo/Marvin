/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.io.xml;

/**
 *
 * @author cdi305
 */
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
