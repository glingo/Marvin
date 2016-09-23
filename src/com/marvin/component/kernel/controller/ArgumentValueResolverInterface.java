package com.marvin.component.kernel.controller;

public interface ArgumentValueResolverInterface<T> {
    
    public boolean support(T request, ArgumentMetadata argument);
    
    public Object resolve(T request, ArgumentMetadata argument);
}
