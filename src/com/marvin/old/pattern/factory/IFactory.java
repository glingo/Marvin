/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.pattern.factory;

/**
 *
 * @author caill
 */
public interface IFactory {

//    Object instance(Class type, Object[] parameters);
    
    <T> T instance(Class type);
    <T> T instance(Class type, Object[] parameters);
}
