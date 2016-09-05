package com.marvin.component.kernel.controller;

import com.marvin.component.kernel.dialog.Request;

public interface ArgumentValueResolverInterface {
    
    public boolean support(Request request, ArgumentMetadata argument);
    
    public Object resolve(Request request, ArgumentMetadata argument);
}
