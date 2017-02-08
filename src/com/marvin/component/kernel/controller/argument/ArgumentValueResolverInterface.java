package com.marvin.component.kernel.controller.argument;

public interface ArgumentValueResolverInterface<R, T> {
    
    public boolean support(R request, T response, ArgumentMetadata argument);
    
    public Object resolve(R request, T response, ArgumentMetadata argument);
}
