package com.marvin.component.container;

import com.marvin.component.container.exception.ContainerException;
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
     * @throws ContainerException if the service does not exists
     */
    Object get(String id) throws ContainerException;

    <T> T get(String id, Class<T> type) throws ContainerException;

    /* Parameters methods */
    
    ConcurrentMap<String, Object> getParameters();
    
    Object getParameter(String key, Object def);
    
    Object getParameter(String key);
    
    
    /* Definitions methods */
    
}
