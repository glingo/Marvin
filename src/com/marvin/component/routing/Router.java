package com.marvin.component.routing;

import com.marvin.component.routing.xml.XmlRouteReader;
import java.util.HashMap;
import java.util.logging.Level;
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
            this.logger.info("Loading routing");
            this.reader.read(this.resource);
            this.collection = this.reader.getRouteCollection();
            
            this.collection.getRoutes().forEach((name, route) -> {
                String msg = String.format("Route loaded : %s (%s)", name, route.getPath());
                this.logger.info(msg);
            });
        }
        
        return this.collection;
    }
    
    public MatcherInterface getMatcher() {
        return this.matcher;
    }

    public HashMap<String, Object> match(String matchable) {
        return getMatcher().match(getRouteCollection(), matchable);
    }

//    @Override
//    public String toString() {
//        
//        StringBuilder sb = new StringBuilder();
//        
//        sb
//            .append("---")
//            .append(super.toString())
//            .append("---")
//            .append("\n\troutes: \n\t\t")
//            .append(getRouteCollection().getRoutes())
//            .append("\n");
////        getRouteCollection().getRoutes().forEach((String id, Route route) -> {
////            builder.append("\n").append(id).append(": ").append(route);
////        });
//        
//        return sb.toString();
//    }
    
//    public Route find(String path) {
//        return getRouteCollection().getRoutes().values().stream().filter((Route filtered) -> {
//            return filtered.getPath().matches(path);
//            return filtered.getPath().matches(path);
//        }).findFirst().orElse(null);
//    }

}
