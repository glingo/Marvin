package com.marvin.old.pattern.builder;

public interface IBuilder<P> {
    
    P getProduct();
    
    void build();
    
}
