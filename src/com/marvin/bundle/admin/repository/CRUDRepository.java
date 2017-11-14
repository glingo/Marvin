package com.marvin.bundle.admin.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CRUDRepository<T> {
    
    private final Class<T> type;
    
    private Map<Long, T> values;

    public CRUDRepository(Class type) {
        this.type = type;
        this.values = new HashMap<>();
    }
    
    public T save(T value) {
//        if (Objects.isNull(value.getId())) {
//            value.setId(Integer.toUnsignedLong(this.values.size()));
//        }
        return this.values.put(Integer.toUnsignedLong(this.values.size()), value);
    }
    
    public T find(Long id) {
        return this.values.get(id);
    }
    
    public List<T> findAll() {
        return new ArrayList<>(this.values.values());
    }
}
