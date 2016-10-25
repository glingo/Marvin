package com.marvin.bundle.framework.controller.argument;

public interface ArgumentValueResolverInterface<T> {
    
    public boolean support(T request, ArgumentMetadata argument);
    
    public Object resolve(T request, ArgumentMetadata argument);
}
