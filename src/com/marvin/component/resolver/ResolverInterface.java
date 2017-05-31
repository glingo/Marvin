package com.marvin.component.resolver;

@FunctionalInterface
public interface ResolverInterface<I, O> {
    
    O resolve(I object) throws Exception;
}
