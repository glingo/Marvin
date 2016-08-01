/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.pattern.loader;

/**
 *
 * @author cdi305
 * @param <P>
 */
public abstract class Loader<P> implements ILoader<P>{
    
    @Override
    public abstract P load(String location);
    
}
