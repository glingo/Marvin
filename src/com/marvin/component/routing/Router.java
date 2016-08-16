package com.marvin.component.routing;

import com.marvin.component.routing.config.Route;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Router {

    protected ConcurrentMap<String, Route> routes = new ConcurrentHashMap<>();

    public Router() {
        super();
    }

    public void addRoute(String id, Route route) {
        this.routes.putIfAbsent(id, route);
    }

    public Route find(String path) {
        return routes.values().stream().filter((Route filtered) -> {
            return filtered.getPath().matches(path);
        }).findFirst().orElse(null);
    }

    public ConcurrentMap<String, Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ConcurrentMap<String, Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        
        
        StringBuilder builder = new StringBuilder();
        builder.append("\n  Router \n");
        
        builder.append("\n----------------Liste des routes----------------");
        this.getRoutes().forEach((String id, Route route) -> {
            builder.append("\n").append(id).append(": ").append(route);
        });
        
        
        builder.append("\n----------------Fin Router----------------");
        
        return builder.toString();
    }
    
    
    
}
