/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.locator;

/**
 *
 * @author Dr.Who
 * @param <T>
 */
public interface ILocator<T>{ 
    
    boolean supports(String name);
    T locate(String name);
    
}
