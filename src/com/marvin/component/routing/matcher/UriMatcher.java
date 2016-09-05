package com.marvin.component.routing.matcher;

import com.marvin.component.kernel.dialog.Request;
import com.marvin.component.routing.RouteCollection;
import com.marvin.component.routing.config.Route;
import java.util.HashMap;

/**
 *
 * @author caill
 */
public class UriMatcher extends RequestMatcher {

    @Override
    public boolean support(Request request) {
//        request.getUri()
// && request.getUri()..matches("^(\\p{Graph}+)$")
        return (request.getUri() != null);
    }
    
    @Override
    protected HashMap<String, Object> matchCollection(String path, RouteCollection collection) {
        
        Route route = collection.getRoutes().values().stream().filter((Route filtered) -> {
            System.out.println(filtered.getPattern());
            System.out.println(path);
            System.out.println(filtered.getPattern().matcher(path).find());
//            System.out.println(filtered.getPattern().matcher(path).matches());
//            return filtered.getPattern().matcher(path).matches();
            return filtered.getPattern().matcher(path).find();
        }).findFirst().orElse(null);
        
        if(route == null) {
            return null;
        }
        
        HashMap<String, Object> parameters = route.getDefaults();
        
        return parameters;
    }

    @Override
    public HashMap<String, Object> match(RouteCollection collection, String path) {
        return matchCollection(path, collection);
    }

    @Override
    public HashMap<String, Object> matchRequest(RouteCollection collection, Request request) {
        return match(collection, request.getUri().getPath());
    }
    
}
