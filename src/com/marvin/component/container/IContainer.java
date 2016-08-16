package com.marvin.component.container;

import com.marvin.component.container.exception.ContainerException;

/**
 *
 * @author caill
 */
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
       
    /**
     * @param <T>
     * @param id
     * @param type
     * @return 
     * @throws com.marvin.component.container.exception.ContainerException 
     */
    <T> T get(String id, Class<T> type) throws ContainerException;
    
    Object getParameter(String key, Object def);
    
    Object getParameter(String key);
    
}
