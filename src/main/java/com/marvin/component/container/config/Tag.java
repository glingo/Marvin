package com.marvin.component.container.config;

import java.util.HashMap;

public class Tag {

    protected String name;

    protected HashMap<String, Object> parameters;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParameter(String key, Object parameter) {
        getParameters().put(key, parameter);
    }

    public void setParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public HashMap<String, Object> getParameters() {
        if (this.parameters == null) {
            this.parameters = new HashMap<>();
        }
        return parameters;
    }

}
