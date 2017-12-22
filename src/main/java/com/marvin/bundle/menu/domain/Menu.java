package com.marvin.bundle.menu.domain;

import java.util.Collection;

public class Menu {
    
    private Menu parent;
    
    private Collection<Menu> children;
    
    private String name;

    public Menu() {
    }

    public Collection<Menu> getChildren() {
        return children;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }
    
    public void setChildren(Collection<Menu> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
