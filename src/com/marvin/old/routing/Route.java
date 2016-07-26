package com.marvin.old.routing;

public class Route {

    private String controller;
    private String name;
    private String path = "/";

    public Route(String name, String path, String controller) {
        super();
        this.name = name;
        this.path = path;
        this.controller = controller;
    }

    @Override
    public String toString() {
        return getPath();
    }
    
    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
