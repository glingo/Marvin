package com.marvin.component.mvc.view;

import com.marvin.component.resolver.ResolverInterface;

public interface ViewResolverInterface extends ResolverInterface<String, ViewInterface> {
    
    @Override
    ViewInterface resolve(String name) throws Exception;
}
