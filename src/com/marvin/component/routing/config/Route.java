package com.marvin.component.routing.config;

public class Route {

    private String controller;
    private String path = "/";

    public Route() {
        super();
    }
    
    public Route(String path, String controller) {
        super();
        this.path = path;
        this.controller = controller;
    }

    @Override
    public String toString() {
        return getController() + "::" + getPath();
    }
    
    public String getController() {
        return this.controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
