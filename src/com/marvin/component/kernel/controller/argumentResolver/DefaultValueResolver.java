package com.marvin.component.kernel.controller.argumentResolver;

import com.marvin.component.kernel.controller.ArgumentMetadata;
import com.marvin.component.kernel.controller.ArgumentValueResolverInterface;

public class DefaultValueResolver<T> implements ArgumentValueResolverInterface<T> {

    @Override
    public boolean support(T request, ArgumentMetadata argument) {
        return argument.hasDefaultValue();
    }

    @Override
    public Object resolve(T request, ArgumentMetadata argument) {
        return argument.getDefaultValue();
    }
    
}
