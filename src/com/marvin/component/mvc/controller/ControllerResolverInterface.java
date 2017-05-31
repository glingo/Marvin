package com.marvin.component.mvc.controller;

import com.marvin.component.resolver.ResolverInterface;

public interface ControllerResolverInterface<I> extends ResolverInterface<I, ControllerReference> {

    @Override
    ControllerReference resolve(I object) throws Exception;
}
