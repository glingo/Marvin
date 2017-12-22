package com.marvin.component.data.manager;

import java.sql.Connection;

public abstract class EntityManager {
    
    private final Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }
    
    public abstract void persist(Object instance);
    
    public abstract void delete();
    
    public abstract void flush();

    public Connection getConnection() {
        return connection;
    }
    
}
