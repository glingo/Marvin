/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.routing;

import com.marvin.component.routing.config.Route;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author caill
 */
public class RouteCollection {

    protected ConcurrentMap<String, Route> routes;

    public void addRoute(String id, Route route) {
        getRoutes().putIfAbsent(id, route);
    }

    public ConcurrentMap<String, Route> getRoutes() {

        if (this.routes == null) {
            setRoutes(new ConcurrentHashMap<>());
        }

        return this.routes;
    }

    public void setRoutes(ConcurrentMap<String, Route> routes) {
        this.routes = routes;
    }
}
