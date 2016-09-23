package com.marvin.old.dialog.controller.argumentResolver;

import com.marvin.component.util.ClassUtils;
import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.controller.ArgumentMetadata;
import com.marvin.old.dialog.controller.ArgumentValueResolverInterface;

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
