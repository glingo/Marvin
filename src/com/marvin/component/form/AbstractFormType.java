package com.marvin.component.form;

import com.marvin.component.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public abstract class AbstractFormType<T> implements FormTypeInterface<T> {
    
    private int index;
    
    private FormTypeInterface parent;
    private List<FormTypeInterface> children;
    
    private String name;
    private String label;
    private T data;
    
    public AbstractFormType() {
        this.name = UUID.randomUUID().toString();
    }
    
    public AbstractFormType(String name) {
        this.name = name;
    }
    
    public AbstractFormType(String name, String label, T data) {
        this.name = name;
        this.label = label;
        this.data = data;
    }
    
    @Override
    public FormBuilder createBuilder() {
        return new FormBuilder(getName(), this);
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void visit(Consumer<FormTypeInterface> visitor) {
        this.getChildren().stream().forEach(visitor);
    }
    
    @Override
    public List<FormTypeInterface> getChildren() {
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        
        return this.children;
    }

    public void setChildren(List<FormTypeInterface> children) {
        this.children = children;
    }
    
    @Override
    public void addChild(FormTypeInterface child) {
        getChildren().add(child);
        child.setParent(this);
    }
    
    @Override
    public void addChildren(List<FormTypeInterface> children) {
        children.forEach(this::addChild);
    }

    @Override
    public void setParent(FormTypeInterface parent) {
        this.index = 1 + parent.getIndex();
        this.parent = parent;
    }
    
    @Override
    public FormTypeInterface getParent() {
        return this.parent;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        String newLine = "\n";
        int i = this.index;
        while(i-- > 0) {
            newLine += "\t";
        }
            
        sb
            .append("{")
            .append(newLine)
            .append("type : ")
            .append(StringUtils.quote(getClass().getSimpleName()))
            .append(", ") 
            .append(newLine)
            .append("name : ")
            .append(StringUtils.quote(getName()))
            .append(", ") 
            .append(newLine)
            .append("index : ")
            .append(getIndex())
            .append(", ") 
            .append(newLine)
            .append("label : ")
            .append(StringUtils.quote(getLabel()))
            .append(", ") 
            .append(newLine)
            .append("data : ")
            .append(StringUtils.quoteIfString(getData()))
            .append(", ") 
            .append(newLine)
            .append("children : ")
            .append(getChildren())
            .append(newLine)
            .append("}")
        ;
        
        return sb.toString();
    }
}
