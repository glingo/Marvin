package com.marvin.component.routing;

import com.marvin.component.kernel.dialog.Request;
import com.marvin.component.routing.xml.XmlRouteReader;
import java.util.HashMap;

public class Router {

    private XmlRouteReader reader;
    private String resource;
    private RouteCollection collection;
    private RequestMatcherInterface matcher;
    
    public Router(RouteCollection collection, RequestMatcherInterface matcher) {
        super();
        this.collection = collection;
        this.matcher = matcher;
    }

    public Router(XmlRouteReader reader, String resource, RequestMatcherInterface matcher) {
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
    
    public RequestMatcherInterface getMatcher() {
        return this.matcher;
    }

    public HashMap<String, Object> matchRequest(Request request) {
        return getMatcher().matchRequest(getRouteCollection(), request);
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("---")
            .append(super.toString())
            .append("---")
            .append("\n\troutes: \n\t\t")
            .append(getRouteCollection().getRoutes())
            .append("\n");
//        getRouteCollection().getRoutes().forEach((String id, Route route) -> {
//            builder.append("\n").append(id).append(": ").append(route);
//        });
        
        return sb.toString();
    }
    
//    public Route find(String path) {
//        return getRouteCollection().getRoutes().values().stream().filter((Route filtered) -> {
//            return filtered.getPath().matches(path);
//            return filtered.getPath().matches(path);
//        }).findFirst().orElse(null);
//    }
}
