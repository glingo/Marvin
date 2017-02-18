package com.marvin.component.routing.xml;

import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.io.IResource;
import com.marvin.component.io.xml.DocumentLoader;
import com.marvin.component.io.xml.XMLReader;
import com.marvin.component.routing.RouteCollection;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlRouteReader extends XMLReader {

    protected RouteCollection collection;
    protected XmlRouteDocumentReader documentReader;
    
    public XmlRouteReader() {
        super();
    }
    
    public XmlRouteReader(ResourceLoader loader) {
        super(loader, new DocumentLoader());
        this.documentReader = new XmlRouteDocumentReader(this.context);
    }
    
    public XmlRouteReader(RouteCollection collection) {
        this();
        this.collection = collection;
    }
    
    public XmlRouteReader(RouteCollection collection, ResourceLoader loader) {
        this(loader);
        this.collection = collection;
    }

    public RouteCollection read(String location, RouteCollection collection) {
        this.collection = collection;
        super.read(location);
        return getRouteCollection();
    }
    
    @Override
    protected void doRead(InputSource inputSource, IResource resource) throws Exception {
        Document doc = doLoadDocument(inputSource, resource);
        registerRoutes(doc, resource);
    }
    
    public void registerRoutes(Document doc, IResource resource) {
        this.documentReader.registerRoutes(doc, getRouteCollection());
    }
    
    public RouteCollection getRouteCollection(){
        if(this.collection == null) {
            this.collection = new RouteCollection();
        }
        return this.collection;
    }
}
