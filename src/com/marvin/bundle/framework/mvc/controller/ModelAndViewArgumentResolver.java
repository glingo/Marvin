package com.marvin.bundle.framework.mvc.controller;

import com.marvin.bundle.framework.mvc.model.Model;
import com.marvin.component.kernel.controller.argument.ArgumentMetadata;
import com.marvin.component.kernel.controller.argument.ArgumentValueResolverInterface;
import com.marvin.component.util.ClassUtils;

public class ModelAndViewArgumentResolver<R, T> implements ArgumentValueResolverInterface<R, T> {
    
    private Model model;
    
    @Override
    public boolean support(R request, T response, ArgumentMetadata argument) {
        return ClassUtils.isAssignable(argument.getType(), Model.class);
    }

    @Override
    public Object resolve(R request, T response, ArgumentMetadata argument) {
        if(null == this.model) {
            this.model = new Model();
        }
        return this.model;
    }
}
