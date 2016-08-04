/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container;

import com.marvin.component.container.exception.ContainerException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author caill
 */
public class Container implements IContainer {
    
    /** The map where we stack aliases. */
    protected ConcurrentMap<String, String> aliases;
    
    /** The map where we stack services instances. */
    protected ConcurrentMap<String, Object> services;
    
    /** The map where we stack parameters. */
    protected ConcurrentMap<String, Object> parameters;
    
    /** Constructor whithout any arguments */
    public Container() {
        this.aliases     = new ConcurrentHashMap<>();
        this.services    = new ConcurrentHashMap<>();
        this.parameters  = new ConcurrentHashMap<>();
    }
    
// *************************************************************************
// *                        CORE methods                                   *
// *************************************************************************
    
    @Override
    public Object getParameter(String key, Object def){
        return this.parameters.getOrDefault(key, def);
    }
    
    @Override
    public Object getParameter(String key){
        return this.parameters.get(key);
    }
    
    public void setParameter(String key, Object value) {
        this.parameters.put(key, value);
    }

    public ConcurrentMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(ConcurrentMap<String, Object> parameters) {
        this.parameters = parameters;
    }
    
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
            this.services.remove(id);
            return;
        }
        
        // inject service in map of services if it is not already set
        this.services.putIfAbsent(id, service);
        
    }
    
    /**
     * @throws com.marvin.component.container.exception.ContainerException
     * @see IContainer#get(java.lang.String)  
     */
    @Override
    public Object get(String id) throws ContainerException {
        
        // look into aliases map
        if(this.aliases.containsKey(id)) {
            // this is an alias, retrive the real id
            id = this.aliases.get(id);
        }
        
        // check if container have this service
        if(!this.services.containsKey(id)) {
            // the service does not exists throw an exception
            String msg = String.format("Service %s not found in container.", id);
            throw new ContainerException(msg);
        }
        
        // return the service
        return this.services.get(id);
    }

    @Override
    public <T> T get(String id, Class<T> type) throws ContainerException {
        return (T) this.get(id);
    }
    
// *************************************************************************
// *                      GETTERS and SETTERS                              *
// *************************************************************************

    public ConcurrentMap<String, String> getAliases() {
        return aliases;
    }

    public void setAliases(ConcurrentMap<String, String> aliases) {
        this.aliases = aliases;
    }

    public ConcurrentMap<String, Object> getServices() {
        return services;
    }

    public void setServices(ConcurrentMap<String, Object> services) {
        this.services = services;
    }
    
}
