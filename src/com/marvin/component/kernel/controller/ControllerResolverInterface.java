package com.marvin.bundle.framework.controller;

public interface ControllerResolverInterface<T> {
    
    ControllerReference resolveController(T request) throws Exception;
    
}
