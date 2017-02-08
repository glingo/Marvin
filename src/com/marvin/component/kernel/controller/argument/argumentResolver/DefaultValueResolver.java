package com.marvin.component.kernel.controller.argument.argumentResolver;

import com.marvin.component.kernel.controller.argument.ArgumentMetadata;
import com.marvin.component.kernel.controller.argument.ArgumentValueResolverInterface;

public class DefaultValueResolver<R, T> implements ArgumentValueResolverInterface<R, T> {

    @Override
    public boolean support(R request, T response, ArgumentMetadata argument) {
        return argument.hasDefaultValue();
    }

    @Override
    public Object resolve(R request, T response, ArgumentMetadata argument) {
        return argument.getDefaultValue();
    }
    
}
