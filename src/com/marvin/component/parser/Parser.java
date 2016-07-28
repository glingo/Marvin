/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
