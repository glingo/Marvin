package com.marvin.old.pattern.factory;

public interface IFactory {

//    Object instance(Class type, Object[] parameters);
    
    <T> T instance(Class type);
    <T> T instance(Class type, Object[] parameters);
}
