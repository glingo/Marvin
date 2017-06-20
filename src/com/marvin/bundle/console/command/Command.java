package com.marvin.bundle.console.command;

import java.util.HashMap;
import java.util.Map;

public class Command {
    
    private Map<String, Object> parameters;
    
    private String line;

    public Command() {
        this.parameters = new HashMap();
    }

    public Command(String line) {
        this();
        this.line = line;
    }
    
    public Command(String line, Map<String, Object> parameters) {
        this.parameters = parameters;
        this.line = line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
    
    public Object getParameter(String name) {
        return getParameter(name, null);
    }
    
    public Object getParameter(String name, Object orElse) {
        return this.parameters.getOrDefault(name, orElse);
    }

    @Override
    public String toString() {
        return "Command{" + "parameters=" + parameters + ", line=" + line + '}';
    }
}
