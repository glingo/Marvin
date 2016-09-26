package com.marvin.component.routing.xml;

import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.io.IResource;
import com.marvin.component.io.xml.DocumentLoader;
import com.marvin.component.io.xml.XMLReader;
import com.marvin.component.routing.RouteCollection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlRouteReader extends XMLReader {

    protected RouteCollection collection;
    
    public XmlRouteReader() {
        super();
    }
    
    public XmlRouteReader(ResourceLoader loader) {
        super(loader, new DocumentLoader());
    }
    
    public XmlRouteReader(RouteCollection collection) {
        super();
        this.collection = collection;
    }
    
    public XmlRouteReader(RouteCollection collection, ResourceLoader loader) {
        super(loader, new DocumentLoader());
        this.collection = collection;
    }

    public RouteCollection read(String location, RouteCollection collection) {
        this.collection = collection;
        super.read(location);
        return getRouteCollection();
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
        XmlRouteDocumentReader documentReader = new XmlRouteDocumentReader(this.context);
        documentReader.registerRoutes(doc, getRouteCollection());
    }
    
    public RouteCollection getRouteCollection(){
        if(this.collection == null) {
            this.collection = new RouteCollection();
        }
        return this.collection;
    }
}
