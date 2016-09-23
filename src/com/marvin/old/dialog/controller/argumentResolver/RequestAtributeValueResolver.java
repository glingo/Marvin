package com.marvin.old.dialog.controller.argumentResolver;

import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.controller.ArgumentMetadata;
import com.marvin.old.dialog.controller.ArgumentValueResolverInterface;

public class RequestAtributeValueResolver implements ArgumentValueResolverInterface  {

    @Override
    public boolean support(Request request, ArgumentMetadata argument) {
        return !argument.isIsVariadic() && request.getAttributes().containsKey(argument.getName());
    }

    @Override
    public Object resolve(Request request, ArgumentMetadata argument) {
        return request.getAttribute(argument.getName());
    }
    
}
