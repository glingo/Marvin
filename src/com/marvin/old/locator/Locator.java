package com.marvin.old.locator;

/**
 *
 * @author Dr.Who
 * @param <T>
 */
public abstract class Locator<T> implements ILocator<T> {
    
    @Override
    public boolean supports(String name) {
//        A null resource is not valid to be located.
//        An empty name is not valid to be located.
        return null != name && !"".equals(name);
    }
    
}
