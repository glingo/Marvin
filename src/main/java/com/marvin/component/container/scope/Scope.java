package com.marvin.component.container.scope;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Scope {
    
    protected ScopeBuilderInterface parent;
    
    protected List<ScopeBuilderInterface> children;
    
    /** The map where we stack aliases. */
    protected ConcurrentMap<String, String> aliases;
    
    /** The map where we stack services instances. */
    protected ConcurrentMap<String, Object> services;
    
    /** The map where we stack parameters. */
    protected ConcurrentMap<String, Object> parameters;
    
    public Scope() {
        super();
        this.parameters = new ConcurrentHashMap<>();
        this.aliases = new ConcurrentHashMap<>();
        this.services = new ConcurrentHashMap<>();
    }
    
    public static ScopeBuilderInterface builder() {
        return new ScopeBuilder();
    }
    
    /** 
     * Constructor whith a map of parameters.
     * @param parameters 
     */
    public Scope(ConcurrentMap<String, Object> parameters) {
        setParameters(parameters);
    }
    
    /* Services */

    public boolean contains(String id){
        return getServices().containsKey(id);
    }
    
    public ConcurrentMap<String, Object> getServices() {
        if(this.services == null) {
            setServices(new ConcurrentHashMap<>());
        }
        
        return this.services;
    }

    public final void setServices(ConcurrentMap<String, Object> services) {
        this.services = services;
    }
    
    /* Aliases */
        
    public ConcurrentMap<String, String> getAliases() {
        if(this.aliases == null) {
            setAliases(new ConcurrentHashMap<>());
        }
        
        return aliases;
    }

    public void setAliases(ConcurrentMap<String, String> aliases) {
        this.aliases = aliases;
    }
    
    public void addAlias(String id, String alias) {
        getAliases().put(id, alias);
    }
    
    /* Parameters */
    
    public Object getParameterWithDefault(String key, Object def){
        return getParameters().getOrDefault(key, def);
    }
    
    public <T> T getParameterWithDefault(String key, Object def, Class<T> type){
        return (T) getParameterWithDefault(key, def);
    }
    
    public Object getParameter(String key){
        return getParameters().get(key);
    }

    public <T> T getParameter(String key, Class<T> type) {
        return (T) getParameterWithDefault(key, null);
    }
    
    public void setParameter(String key, Object value) {
        getParameters().put(key, value);
    }
    
    public ConcurrentMap<String, Object> getParameters() {
        if(this.parameters == null) {
            setParameters(new ConcurrentHashMap<>());
        }
        
        return this.parameters;
    }

    public final void setParameters(ConcurrentMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void setChildren(List<ScopeBuilderInterface> children) {
        this.children = children;
    }

    public ScopeBuilderInterface getParent() {
        return parent;
    }

    public void setParent(ScopeBuilderInterface parent) {
        this.parent = parent;
    }

    public List<ScopeBuilderInterface> getChildren() {
        return children;
    }
    
}
