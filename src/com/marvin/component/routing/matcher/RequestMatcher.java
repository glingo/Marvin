package com.marvin.component.routing.matcher;

import com.marvin.component.dialog.Request;
import com.marvin.component.routing.RequestMatcherInterface;
import com.marvin.component.routing.RouteCollection;
import com.marvin.component.routing.config.Route;
import java.util.HashMap;
import java.util.regex.Matcher;

public abstract class RequestMatcher implements RequestMatcherInterface {

    @Override
    public abstract boolean support(Request request);

    @Override
    public abstract HashMap<String, Object> matchRequest(RouteCollection collection, Request request);

    protected HashMap<String, Object> matchCollection(String path, RouteCollection collection) {
        
        Route route = collection.getRoutes().values().stream().filter((Route filtered) -> {
            return filtered.getPattern().matcher(path).find();
        }).findFirst().orElse(null);
        
        if(route == null) {
            return null;
        }
        
        HashMap<String, Object> parameters = route.getDefaults();
        parameters.put("_route", path);
        
        Matcher matcher = route.getPattern().matcher(path);
        
        if(matcher.matches()) {
            route.getVariableNames().stream().forEach((variable) -> {
                parameters.put(variable, matcher.group(variable));
            });
        }
        
        return parameters;
    }
    
    public HashMap<String, Object> match(RouteCollection collection, String path) {
        return matchCollection(path, collection);
    }

}
