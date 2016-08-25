package com.marvin.component.container.config;

import com.marvin.component.util.ObjectUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Definition {
    
    protected String scope;
    
    protected String className;
    
    protected String factoryName;
    
    protected String factoryMethodName;
    
    protected String parentName;
    
    protected String description;
    
    protected Boolean deprecated;
    
    protected String[] aliases;
    
    protected Object[] arguments;
    
    protected String[] tags;
    
    protected LinkedHashMap<String, List<Object[]>> calls;

    public Definition() {
        this.arguments = new Object[]{};
    }
    
    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFactoryMethodName() {
        return this.factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeprecated() {
        return this.deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
    
    public void replaceArgument(int index, Object argument) {
        
        if(getArguments() == null) {
            setArguments(new Object[index]);
        }
        
        if(index < getArguments().length - 1) {
            this.arguments[index] = argument;
        }
    }
    
    public void addArgument(Object argument) {
        
        if(arguments == null) {
            setArguments(new Object[0]);
        }
        
        this.arguments = ObjectUtils.addObjectToArray(arguments, argument);
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryName() {
        return this.factoryName;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getTags() {
        return this.tags;
    }
    
    public void addTag(String tag) {
        if(this.tags == null) {
            this.tags = new String[0];
        }
        
        this.tags = ObjectUtils.addObjectToArray(this.tags, tag);
    }
    
    public boolean hasTag(String name){
        
        if(getTags() == null) {
            return false;
        }
        
        for (String tag : getTags()) {
            if(name.equals(tag)) {
                return true;
            }
        }
        
        return false;
    }

    public void setCalls(LinkedHashMap<String, List<Object[]>> calls) {
        this.calls = calls;
    }

    public LinkedHashMap<String, List<Object[]>> getCalls() {
        return this.calls;
    }
    
    public void addCall(String name, Object... args) {
        
        if(getCalls() == null) {
            setCalls(new LinkedHashMap<>());
        }
        
        if(!getCalls().containsKey(name)) {
            getCalls().put(name, new ArrayList<>());
        }
        
        getCalls().get(name).add(args);
    }
    
     public boolean hasCall() {
        return this.calls != null;
    }
}
