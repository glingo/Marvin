package com.marvin.old.pattern.loader;

/**
 *
 * @author cdi305
 * @param <P>
 */
public abstract class Loader<P> implements ILoader<P>{
    
    @Override
    public abstract P load(String location);
    
}
