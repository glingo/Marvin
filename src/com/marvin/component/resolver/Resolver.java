package com.marvin.component.resolver;

import java.util.logging.Logger;

public abstract class Resolver<I, O> implements ResolverInterface<I, O> {

    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    @Override
    public abstract O resolve(I object) throws Exception;
}
