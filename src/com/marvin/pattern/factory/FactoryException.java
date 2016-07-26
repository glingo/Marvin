/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.pattern.factory;

/**
 *
 * @author cdi305
 */
public class FactoryException extends Exception {
    
    protected Object context;

    public FactoryException() {}

    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(Throwable cause) {
        super(cause);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    public FactoryException(Object context, String message) {
        super(message);
        this.context = context;
    }
    
    public FactoryException(Object context, String message, Throwable cause) {
        super(message, cause);
        this.context = context;
    }
    
}
