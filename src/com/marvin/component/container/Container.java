package com.marvin.component.container;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import com.marvin.component.container.exception.ContainerException;

public class Container implements IContainer {
    
    /** The map where we stack aliases. */
    protected ConcurrentMap<String, String> aliases;
    
    /** The map where we stack services instances. */
    protected ConcurrentMap<String, Object> services;
    
    /** The map where we stack parameters. */
    protected ConcurrentMap<String, Object> parameters;
    
    /** 
     * Constructor whithout any arguments 
     */
    public Container() {
    
    }
    
    /** 
     * Constructor whith a map of parameters
     * @param parameters 
     */
    public Container(ConcurrentMap<String, Object> parameters) {
        setParameters(parameters);
    }
    
// *************************************************************************
// *                        CORE methods                                   *
// *************************************************************************

    /** @see IContainer#get(java.lang.String) */
    @Override
    public void set(String id, Object service) {
        
        // check if id is null or empty
        if(id == null || "".equals(id)){
            return;
        }
        
        // reference to the id are case-sensitives
        id = id.toLowerCase();
        
        // in case of null object
        if(null == service) {
            // remove it from map
            getServices().remove(id);
            return;
        }
        
        // inject service in map of services if it is not already set
        getServices().putIfAbsent(id, service);
        
    }
    
    /**
     * @throws com.marvin.component.container.exception.ContainerException
     * @see IContainer#get(java.lang.String)  
     */
    @Override
    public Object get(String id) throws ContainerException {
        
        // look into aliases map
        if(getAliases().containsKey(id)) {
            // this is an alias, retrive the real id
            id = getAliases().get(id);
        }
        
        // check if container have this service
        if(!this.contains(id)) {
            // the service does not exists throw an exception
            String msg = String.format("Service %s not found in container.", id);
            throw new ContainerException(msg);
        }
        
        // return the service
        return getServices().get(id);
    }

    @Override
    public <T> T get(String id, Class<T> type) throws ContainerException {
        return (T) this.get(id);
    }
    
    public boolean contains(String id){
        return getServices() != null && getServices().containsKey(id);
    }
    
// *************************************************************************
// *                      GETTERS and SETTERS                              *
// *************************************************************************

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
    
    /* Services */

    public ConcurrentMap<String, Object> getServices() {
        
        if(this.services == null) {
            setServices(new ConcurrentHashMap<>());
        }
        
        return this.services;
    }

    public void setServices(ConcurrentMap<String, Object> services) {
        this.services = services;
    }
    
    /* Parameters */
    
    @Override
    public Object getParameter(String key, Object def){
        return getParameters().getOrDefault(key, def);
    }
    
    @Override
    public Object getParameter(String key){
        return getParameters().get(key);
    }
    
    public void setParameter(String key, Object value) {
        getParameters().put(key, value);
    }

    @Override
    public ConcurrentMap<String, Object> getParameters() {
        
        if(this.parameters == null) {
            setParameters(new ConcurrentHashMap<>());
        }
        
        return this.parameters;
    }

    public final void setParameters(ConcurrentMap<String, Object> parameters) {
        this.parameters = parameters;
    }
    
    
    /* others */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n  Container \n");
        
        builder.append("\n----------------Liste des parametres----------------");
        this.getParameters().forEach((String id, Object parameter) -> {
            builder.append("\n").append(id).append(": ").append(parameter);
        });
        
        builder.append("\n----------------Liste des services----------------");
        this.getServices().forEach((String id, Object service) -> {
            builder.append("\n").append(id).append(": ").append(service);
        });
        
        
        builder.append("\n----------------Fin Container----------------");
        
        return builder.toString();
    }
}
