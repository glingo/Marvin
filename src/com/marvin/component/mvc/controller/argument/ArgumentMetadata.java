package com.marvin.component.mvc.controller.argument;

public class ArgumentMetadata {
    
    private String name;
    private Class type;
    private boolean isVariadic;
    private boolean hasDefaultValue = false;
    private Object defaultValue;

    public ArgumentMetadata(String name, Class type, boolean isVariadic) {
        this.name = name;
        this.type = type;
        this.isVariadic = isVariadic;
    }
    
    public ArgumentMetadata(String name, Class type, boolean isVariadic, Object defaultValue) {
        this.name = name;
        this.type = type;
        this.isVariadic = isVariadic;
        this.hasDefaultValue = true;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public boolean isIsVariadic() {
        return isVariadic;
    }

    public void setIsVariadic(boolean isVariadic) {
        this.isVariadic = isVariadic;
    }

    public boolean hasDefaultValue() {
        return hasDefaultValue;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        this.hasDefaultValue = true;
    }
    
    
}
