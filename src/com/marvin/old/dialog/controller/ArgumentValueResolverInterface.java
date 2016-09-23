package com.marvin.old.dialog.controller;

import com.marvin.old.dialog.Request;

public interface ArgumentValueResolverInterface {
    
    public boolean support(Request request, ArgumentMetadata argument);
    
    public Object resolve(Request request, ArgumentMetadata argument);
}
