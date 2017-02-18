package com.marvin.component.routing.matcher;

import com.marvin.component.routing.MatcherInterface;
import com.marvin.component.routing.Route;
import com.marvin.component.routing.RouteCollection;
import java.util.HashMap;
import java.util.regex.Matcher;

public class PathMatcher implements MatcherInterface {

    @Override
    public boolean support(String matchable) {
        return matchable != null && !matchable.equals("");
    }
    
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
    
    @Override
    public HashMap<String, Object> match(RouteCollection collection, String path) {
        return matchCollection(path, collection);
    }

}
