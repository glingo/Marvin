package com.marvin.bundle.admin.business;

import com.marvin.bundle.admin.repository.CRUDRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUDManager<T> {
    
    private final CRUDRepository<T> repository;
    private final Class<T> type;

    public CRUDManager(Class type, CRUDRepository<T> repository) {
        this.type = type;
        this.repository = repository;
    }

    public Class<T> getType() {
        return type;
    }
    
    public T create() {
        
        try {
            return this.type.newInstance();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CRUDManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CRUDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public T save(T value) {
        return this.repository.save(value);
    }
    
    public List<T> findAll() {
        return this.repository.findAll();
    }
    
    public T findById(Long id) {
        return this.repository.find(id);
    }
}
