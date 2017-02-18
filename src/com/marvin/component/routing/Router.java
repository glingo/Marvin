package com.marvin.component.routing;

import com.marvin.component.routing.xml.XmlRouteReader;
import java.util.HashMap;
import java.util.logging.Logger;

public class Router {
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private String resource;
    private XmlRouteReader reader;
    private RouteCollection collection;
    private final MatcherInterface matcher;
    
    public Router(RouteCollection collection, MatcherInterface matcher) {
        super();
        this.collection = collection;
        this.matcher = matcher;
    }

    public Router(XmlRouteReader reader, String resource, MatcherInterface matcher) {
        super();
        this.reader = reader;
        this.resource = resource;
        this.matcher = matcher;
    }
    
    public RouteCollection getRouteCollection() {
        if(this.collection == null) {
            this.reader.read(this.resource);
            this.collection = this.reader.getRouteCollection();
        }
        
        return this.collection;
    }
    
    public MatcherInterface getMatcher() {
        return this.matcher;
    }

    public HashMap<String, Object> match(String matchable) {
        return getMatcher().match(getRouteCollection(), matchable);
    }
}
