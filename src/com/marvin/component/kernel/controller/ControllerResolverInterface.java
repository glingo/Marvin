package com.marvin.component.kernel.controller;

public interface ControllerResolverInterface<T> {
    
    ControllerReference resolveController(T request) throws Exception;
}
