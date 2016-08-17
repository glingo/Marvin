package com.marvin.component.container.config;

import com.marvin.component.util.ObjectUtils;
import java.util.LinkedHashMap;

/**
 *
 * @author cdi305
 */
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
    
    protected LinkedHashMap<String, Object[]> calls;

    public Definition() {}
    
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFactoryMethodName() {
        return factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
    
    public void replaceArgument(int index, Object argument) {
        
        if(arguments == null) {
            this.arguments = new Object[index];
        }
        
        if(index < this.arguments.length - 1) {
            this.arguments[index] = argument;
        }
    }
    
    public void addArgument(Object argument) {
        if(arguments == null) {
            this.arguments = new Object[0];
        }
        
        this.arguments = ObjectUtils.addObjectToArray(arguments, argument);
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getTags() {
        return tags;
    }
    
    public void addTag(String tag) {
        if(this.tags == null) {
            this.tags = new String[0];
        }
        
        this.tags = ObjectUtils.addObjectToArray(this.tags, tag);
    }
    
    public boolean hasTag(String name){
        if(this.tags == null) {
            return false;
        }
        
        for (String tag : this.tags) {
            if(name.equals(tag)) {
                return true;
            }
        }
        
        return false;
    }

    public void setCalls(LinkedHashMap<String, Object[]> calls) {
        this.calls = calls;
    }

    public LinkedHashMap<String, Object[]> getCalls() {
        return calls;
    }
    
    public void addCall(String name, Object[] args) {
        if(this.calls == null) {
            this.calls = new LinkedHashMap<>();
        }
        this.calls.put(name, args);
    }
    
     public boolean hasCall() {
        return this.calls != null;
    }
}
