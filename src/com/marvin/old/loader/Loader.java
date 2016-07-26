package com.marvin.old.loader;

import com.marvin.old.builder.Builder;
import com.marvin.old.locator.ILocator;

/**
 *
 * @author Dr.Who
 * @param <T>
 * @param <P>
 * @param <M>
 */
public abstract class Loader<T, P, M> implements LoaderInterface<T, M> {
    
    protected ILocator<T> locator;
    protected Builder<P, M> builder;
  
    public Loader(ILocator<T> locator, Builder<P, M> builder) {
        this.locator = locator;
        this.builder = builder;
    }
    
    @Override
    public void load(String name) {
        T resource = this.locator.locate(name);
        this.load(resource);
    }

    @Override
    public abstract void load(T resource);

}
