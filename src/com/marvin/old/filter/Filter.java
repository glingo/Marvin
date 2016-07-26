/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.filter;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dr.Who
 * @param <T>
 */
public abstract class Filter<T> {
    
    protected String name;
    protected List<String> skips;
    
    public Filter(String name) {
        this.name = name;
    }

    public Filter(String name, List<String> skips) {
        this.name = name;
        this.skips = skips;
    }
    
    public boolean accept(String name){
        return this.name != null && this.name.equals(name);
    }
    
    public boolean skip(String name){
        return this.skips != null && this.skips.contains(name);
    }
}
