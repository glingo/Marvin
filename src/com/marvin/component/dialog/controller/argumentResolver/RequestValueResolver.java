package com.marvin.component.dialog.controller.argumentResolver;

import com.marvin.component.dialog.controller.ArgumentMetadata;
import com.marvin.component.dialog.controller.ArgumentValueResolverInterface;
import com.marvin.component.dialog.Request;
import com.marvin.component.util.ClassUtils;

public class RequestValueResolver implements ArgumentValueResolverInterface  {

    @Override
    public boolean support(Request request, ArgumentMetadata argument) {
        return ClassUtils.isAssignable(Request.class, argument.getType());
    }

    @Override
    public Object resolve(Request request, ArgumentMetadata argument) {
        return request;
    }
    
}
