package com.marvin.old.routing;

import java.util.Map;
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
        }).findFirst().get();
    }
    
    public Map<String, Route> all() {
        return this.routes;
    }
}
