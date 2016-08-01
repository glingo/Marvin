/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.xml;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.io.loader.ResourceLoader;

/**
 *
 * @author cdi305
 */
public class XMLReaderContext {
    
    protected XMLDefinitionReader reader;

    public XMLReaderContext(XMLDefinitionReader reader) {
        this.reader = reader;
    }

    public XMLDefinitionReader getReader() {
        return reader;
    }

    public void setReader(XMLDefinitionReader reader) {
        this.reader = reader;
    }
    
    public ContainerBuilder getContainerBuilder() {
        return this.reader.getContainerBuilder();
    }

}
