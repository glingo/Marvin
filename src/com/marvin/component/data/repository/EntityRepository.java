package com.marvin.component.data.repository;

import com.marvin.component.data.manager.EntityManager;
import java.util.List;

public abstract class EntityRepository<T> {
    
    private String entityName;
    
    private EntityManager manager;
    
    private Class clazz;
    
    public abstract T find(Long id);
    
    public abstract List<T> findAll();
    
    
    
}
