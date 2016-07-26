package com.marvin.old.locator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dr.Who
 * @param <T>
 */
public abstract class DelgateLocator<T> implements ILocator<T> {
    
    protected List<ILocator<T>> locators;
    
    public DelgateLocator() {
        this.locators = new ArrayList();
    }
    
    public DelgateLocator(ILocator<T>[] locators) {
        this.locators = Arrays.asList(locators);
    }
    
    public DelgateLocator(List<ILocator<T>> locators) {
        this.locators = locators;
    }

    public void delegate(ILocator<T> locator){
        this.locators.add(locator);
    }

    @Override
    public boolean supports(String name) {
        return this.locators.stream().anyMatch((ILocator<T> locator) -> {
            return locator.supports(name);
        });
    }
    
    @Override
    public T locate(String name) {
        
        ILocator<T> delegate = this.filter(name);
        
        System.out.println(delegate);
        
        if(delegate == null) {
            return null;
        }
        
        return delegate.locate(name);
        
    }

    protected ILocator<T> filter(String name) {
        return this.locators.stream().filter((ILocator<T> locator) -> {
            return locator.supports(name);
        }).findFirst().get();
    }
}
