/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.routing.xml;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.xml.XMLDefinitionDocumentReader;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.DefaultResourceLoader;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.io.resource.IResource;
import com.marvin.component.io.xml.DocumentLoader;
import com.marvin.component.io.xml.XMLReader;
import com.marvin.component.routing.Router;
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
public class XMLRouteReader extends XMLReader {

    protected Router router;

    public XMLRouteReader(Router router) {
        super();
        this.router = router;
    }
    
    public XMLRouteReader(Router router, ResourceLoader loader) {
        super(loader, new DocumentLoader());
        this.router = router;
    }
    
    @Override
    protected void doRead(InputSource inputSource, IResource resource) {
        try {
            Document doc = doLoadDocument(inputSource, resource);
            registerRoutes(doc, resource);
        } catch (Exception ex) {
            Logger.getLogger(XMLDefinitionReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void registerRoutes(Document doc, IResource resource) {
        XMLRouteDocumentReader documentReader = new XMLRouteDocumentReader(this.context);
        documentReader.registerRoutes(doc, router);
    }
}
