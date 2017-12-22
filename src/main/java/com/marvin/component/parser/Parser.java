package com.marvin.component.parser;

public interface Parser<T> {
    
    Object[] getTypeKeys();
    
    T parse(Object parsed);
}
