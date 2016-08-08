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
    
}
