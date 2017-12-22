package com.marvin.component.routing.xml;

import com.marvin.component.resource.ResourceService;
import com.marvin.component.routing.RouteCollection;
import com.marvin.component.xml.DocumentLoader;
import com.marvin.component.xml.XMLReader;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlRouteReader extends XMLReader {

    protected RouteCollection collection;
    protected XmlRouteDocumentReader documentReader;
    
    public XmlRouteReader(ResourceService resourceService) {
        super(resourceService, new DocumentLoader());
        this.documentReader = new XmlRouteDocumentReader(this.context);
    }
    
    public XmlRouteReader(ResourceService resourceService, RouteCollection collection) {
        this(resourceService);
        this.collection = collection;
    }
    
    public XmlRouteReader(RouteCollection collection, ResourceService resourceService) {
        this(resourceService);
        this.collection = collection;
    }

    public RouteCollection read(String location, RouteCollection collection) {
        this.collection = collection;
        super.read(location);
        return getRouteCollection();
    }
    
    @Override
    protected void doRead(InputSource inputSource) throws Exception {
        Document doc = doLoadDocument(inputSource);
        registerRoutes(doc);
    }
    
    public void registerRoutes(Document doc) {
        this.documentReader.registerRoutes(doc, getRouteCollection());
    }
    
    public RouteCollection getRouteCollection(){
        if(this.collection == null) {
            this.collection = new RouteCollection();
        }
        return this.collection;
    }
}
