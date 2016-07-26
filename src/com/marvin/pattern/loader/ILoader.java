/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.pattern.loader;

import com.marvin.component.util.ResourceUtils;

/**
 *
 * @author cdi305
 * @param <P>
 */
public interface ILoader<P> {
    
    /** Pseudo URL prefix for loading from the class path: "classpath:" */
    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

    P load(String location);
    
}
