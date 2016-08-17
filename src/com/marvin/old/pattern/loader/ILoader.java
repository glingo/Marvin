package com.marvin.old.pattern.loader;

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
