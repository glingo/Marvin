package com.marvin.component.dialog.controller;

import com.marvin.component.dialog.Request;

public interface ArgumentValueResolverInterface {
    
    public boolean support(Request request, ArgumentMetadata argument);
    
    public Object resolve(Request request, ArgumentMetadata argument);
}
