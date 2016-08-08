package com.marvin.component.parser;

/**
 *
 * @author cdi305
 * @param <T>
 */
public interface Parser<T> {
    
    Object[] getTypeKeys();
    
    T parse(Object parsed);
}
