/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.controller;

/**
 *
 * @author cdi305
 */
public interface ControllerResolverInterface<T> {
    
    ControllerReference resolveController(T request) throws Exception;
    
}
