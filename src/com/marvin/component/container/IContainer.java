package com.marvin.component.container;

import java.util.concurrent.ConcurrentMap;

public interface IContainer {
    
    /**
     * Inject a service into the map of services.
     * In order to prevent null instances in map of services,
     * if the given service is null, it will be remove from map.
     * 
     * Throw a ContainerException if id given is null or empty.
     * 
     * @param id the String id
     * @param service the Object service
     */
    void set(String id, Object service);
    
    /**
     * Retrive the Object service with the id given.
     * 
     * See if the given id is an alias, 
     * in this case retrive the real id and continue.
     * 
     * 
     * 
     * @param id the String id
     * 
     * @return the Object service
     */
    Object get(String id);

    <T> T get(String id, Class<T> type);
    
    boolean contains(String id);

    /* Parameters methods */
    
    ConcurrentMap<String, Object> getParameters();
    
    Object getParameter(String key);
    
    <T> T getParameter(String key, Class<T> type);
    
    Object getParameterWithDefault(String key, Object defaultValue);
    
    <T> T getParameterWithDefault(String key, Object defaultValue, Class<T> type);
    
}
