package com.marvin.component.container;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import com.marvin.component.container.exception.ContainerException;
import java.util.logging.Logger;

public class Container implements IContainer {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    /** The map where we stack aliases. */
    protected ConcurrentMap<String, String> aliases;
    
    /** The map where we stack services instances. */
    protected ConcurrentMap<String, Object> services;
    
    /** The map where we stack parameters. */
    protected ConcurrentMap<String, Object> parameters;
    
    /** 
     * Constructor whithout any arguments.
     */
    public Container() {
    
    }
    
    /** 
     * Constructor whith a map of parameters.
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
        this.logger.info(String.format("Trying to set (%s:%s) in container.", id, service));
        
        // check if id is null or empty
        if(id == null || "".equals(id)){
            this.logger.info("An id is required to register a service in container.");
            return;
        }
        
        // reference to the id are case-sensitives
        id = id.toLowerCase();
        
        // in case of null object
        if(null == service) {
            this.logger.info("The service you injected in container is null, we removed any instance.");
            // remove it from map
            getServices().remove(id);
            return;
        }
        
        // inject service in map of services if it is not already set
        getServices().putIfAbsent(id, service);
        
        // maybe change this info in case we didn't inject.
        this.logger.info("The service has been injected in container.");
    }
    
    /**
     * @throws com.marvin.component.container.exception.ContainerException
     * @see IContainer#get(java.lang.String)  
     */
    @Override
    public Object get(String id) throws ContainerException {
        this.logger.info(String.format("Trying to get %s from container.", id));
        
        // look into aliases map
        if(getAliases().containsKey(id)) {
            // this is an alias, retrive the real id
            this.logger.info(String.format("The id has been found in aliases : %s:%s.", id, getAliases().get(id)));
            id = getAliases().get(id);
        }
        
        // check if container have this service
        if(!contains(id)) {
            // the service does not exists throw an exception
            String msg = String.format("Service %s not found in container.", id);
            throw new ContainerException(msg);
        }
        
        // return the service
        return getServices().get(id);
    }

    @Override
    public <T> T get(String id, Class<T> type) throws ContainerException {
        return (T) get(id);
    }
    
    @Override
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
    public Object getParameterWithDefault(String key, Object def){
        return getParameters().getOrDefault(key, def);
    }
    
    @Override
    public <T> T getParameterWithDefault(String key, Object def, Class<T> type){
        return (T) getParameterWithDefault(key, def);
    }
    
    @Override
    public Object getParameter(String key){
        return getParameters().get(key);
    }

    @Override
    public <T> T getParameter(String key, Class<T> type) {
        return (T) getParameterWithDefault(key, null);
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

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("\n  Container \n");
//        
//        builder.append("\n----------------Liste des parametres----------------");
//        getParameters().forEach((String id, Object parameter) -> {
//            builder.append("\n").append(id).append(": ").append(parameter);
//        });
//        
//        builder.append("\n----------------Liste des services----------------");
//        getServices().forEach((String id, Object service) -> {
//            if("container".equals(id)) {
//                builder.append("\n").append(id).append(": ").append("service_container");
//                return;
//            }
//            builder.append("\n").append(id).append(": ").append(service);
//        });
//        
//        builder.append("\n----------------Fin Container----------------");
//        
//        return builder.toString();
//    }
}
