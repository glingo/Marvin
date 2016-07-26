package com.marvin.old.builder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A builder abstract class, 
 * it will provide some methods for building an object instanceof ?.
 * 
 * @author Dr.Who
 * @param <P>
 * @param <M>
 */
public abstract class Builder<P, M> implements BuilderInterface<P, M> {
    
    /* The product we build. */
    protected P product;
    
    /* The product we build. */
    protected Map<String, M> materials;

    /* Constructor without any arguments. */
    public Builder() {
        this.materials = new ConcurrentHashMap<>();
    }
    
    /* . */
    public Builder(P product, Map<String, M> materials) {
        this.product = product;
        this.materials = materials;
    }
    
    /* . */
    public Builder(Map<String, M> materials) {
        super();
        this.materials = materials;
    }
    
    /* Provide a Constructor with the product. */
    public Builder(P product) {
        this.materials = new ConcurrentHashMap<>();
        this.product = product;
    }
    
    @Override
    public P build() {
        if(this.product == null){
            this.product = this.create();
        }
        this.materials.forEach(this::prepareMaterial);
        return product;
    }
    
    public abstract void prepareMaterial(String id, M material);
    
    @Override
    public void stock(String k, M material) {
        this.materials.putIfAbsent(k, material);
    }

}
