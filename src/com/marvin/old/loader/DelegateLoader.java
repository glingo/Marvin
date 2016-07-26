/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.loader;

import com.marvin.old.builder.Builder;
import com.marvin.old.locator.ILocator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dr.Who
 * @param <T>
 * @param <P>
 * @param <M>
 */
public class DelegateLoader<T, P, M> extends Loader<T, P, M> {

    protected List<LoaderInterface<T, M>> loaders;
    
    public DelegateLoader(ILocator<T> locator, Builder<P, M> builder) {
        super(locator, builder);
        this.loaders = new ArrayList();
    }

    @Override
    public boolean supports(String name) {
        return this.loaders.stream().anyMatch((LoaderInterface<T, M> loader) -> {
            return loader.supports(name);
        });
    }
    
    public void delegate(LoaderInterface<T, M> loader){
        this.loaders.add(loader);
    }

    protected LoaderInterface<T, M> filter(T resource) {
        return this.loaders.stream().filter((LoaderInterface loader) -> {
            return loader.supports(resource.toString());
        }).findFirst().orElse(null);
    }
    
    @Override
    public void load(T resource) {
        LoaderInterface<T, M> delegate = this.filter(resource);
        if(delegate != null) {
            delegate.load(resource);
        }
    }

}
