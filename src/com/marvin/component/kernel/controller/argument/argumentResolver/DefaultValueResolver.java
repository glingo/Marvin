package com.marvin.bundle.framework.controller.argument.argumentResolver;

import com.marvin.bundle.framework.controller.argument.ArgumentMetadata;
import com.marvin.bundle.framework.controller.argument.ArgumentValueResolverInterface;

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
