package com.marvin.old.dependency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Dr.Who
 */
public class Container {
    
    protected ConcurrentMap<String, String> aliases;
    protected ConcurrentMap<String, Object> services;
    
    public Container() {
        this.aliases = new ConcurrentHashMap<>();
        this.services = new ConcurrentHashMap<>();
    }
    
    public void reset() {
        this.services = new ConcurrentHashMap<>();
        this.aliases = new ConcurrentHashMap<>();
    }
    
    public Object set(String id, Object service) {
        id = id.toLowerCase();
        this.services.putIfAbsent(id, service);
        if(null == service) {
            this.services.remove(id);
        }
        
        return service;
    }
    
    public Object get(String id) throws Exception {
        
        if(this.aliases.containsKey(id)) {
            id = this.aliases.get(id);
        }
        
        if(this.services.containsKey(id)) {
            return this.services.get(id);
        }
        
        throw new Exception(String.format("Service %s not found in container.", id));
    }
    
    public Map<String, Object> all() {
        return this.services;
    }
    
//    /**
//     * Returns true if the given service is defined.
//     *
//     * @param id
//     * @return bool true if the service is defined, false otherwise
//     */
//    public boolean has(String id)
//    {
//        return this.services.containsKey(id);
//        "service_container".equals(id)
//                || this.aliases.containsKey(id)
//                || this.services.containsKey(id);
//    }
}
