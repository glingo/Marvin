/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.loader;

/**
 *
 * @author Dr.Who
 * @param <T>
 * @param <P>
 */
public interface LoaderInterface<T, P> {
    
    boolean supports(String type);
    void load(String ressource);
    void load(T ressource);
}
