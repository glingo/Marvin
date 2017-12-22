package com.marvin.component.container;

import com.marvin.component.container.config.Definition;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import com.marvin.component.container.factory.ServiceFactory;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Container implements IContainer {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    protected ServiceFactory factory;
    
    /** The map where we stack aliases. */
    protected ConcurrentMap<String, String> aliases;
    
    /** The map where we stack services instances. */
    protected ConcurrentMap<String, Object> services;
    
    /** The map where we stack parameters. */
    protected ConcurrentMap<String, Object> parameters;
    
    /** The map where we stack Definitions. */
    protected ConcurrentMap<String, Definition> definitions;
    
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
        // check if id is null or empty
        if(id == null || "".equals(id)){
            this.logger.info("An id is required to register a service in container.");
            return;
        }
        
        // reference to the id are case-sensitives
        id = id.toLowerCase();
        
        // in case of null object
        if(null == service) {
            this.logger.log(Level.INFO, "The service {} you injected in container is null, we removed any instance.", id);
            // remove it from map
            getServices().remove(id);
            return;
        }
        
        // inject service in map of services if it is not already set
        getServices().putIfAbsent(id, service);
    }
    
    /**
     * @see IContainer#get(java.lang.String)  
     */
    @Override
    public Object get(String id) {
        // look into aliases map
        if(getAliases().containsKey(id)) {
            // this is an alias, retrive the real id
            this.logger.info(String.format("The id has been found in aliases : %s:%s.", id, getAliases().get(id)));
            id = getAliases().get(id);
        }
        
        // check if container have this service
        if(!contains(id)) {
            if(!hasDefinition(id)) {
                return null;
            }
            
            Object service = getFactory().instanciate(id, getDefinition(id));
            set(id, service);
        }
        
        // return the service
        return getServices().get(id);
    }

    @Override
    public <T> T get(String id, Class<T> type) {
        return (T) get(id);
    }
    
    @Override
    public boolean contains(String id){
        return getServices().containsKey(id);
    }
    
     /* Definitions methods */
    
    public boolean hasDefinition(String id) {
        return getDefinitions().containsKey(id);
    }
     
    public void addDefinition(String id, Definition definition) {
        getDefinitions().put(id, definition);
    }
    
    public void addDefinitions(Map<String, Definition> definitions) {
        if(definitions == null) {
            return;
        }
        definitions.forEach(this::addDefinition);
    }
    
    public Definition getDefinition(String id) {
        if(!hasDefinition(id)) {
            this.logger.info(id);
            return null;
        }
        return getDefinitions().get(id);
    }
    
// *************************************************************************
// *                      GETTERS and SETTERS                              *
// *************************************************************************
    
    private ServiceFactory getFactory() {
        if(this.factory == null) {
            this.factory = new ServiceFactory(this);
        }
        
        return this.factory;
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
    
    /* Definitions */
    
    public ConcurrentMap<String, Definition> getDefinitions() {
        
        if(this.definitions == null) {
            this.definitions = new ConcurrentHashMap<>();
        }
        
        return this.definitions;
    }

    public void setDefinitions(ConcurrentMap<String, Definition> definitions) {
        this.definitions = definitions;
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
}
