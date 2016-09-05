package com.marvin.component.form;

import java.util.ArrayList;
import java.util.List;

public class Form {
    
    private String name;
    private Form parent;
    private List<Form> children;

    public Form() {}
    
    public Form(String name) {
        this.name = name;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Form> getChildren() {
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        
        return this.children;
    }

    public void setChildren(List<Form> children) {
        this.children = children;
    }
    
    public void addChild(Form child) {
        getChildren().add(child);
        child.setParent(this);
    }
    
    public void addChildren(List<Form> children) {
        children.forEach(this::addChild);
    }

    public void setParent(Form parent) {
        this.parent = parent;
    }
    
    public Form getParent() {
        return this.parent;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("\n---")
            .append(super.toString())
            .append("---")
            .append("\n\tname : \n\t\t")
            .append(this.name) 
            .append("\n\tchildren : \n\t\t")
            .append(getChildren())
            .append("---- * ----\n");
        
        return sb.toString();
    }
    
}
