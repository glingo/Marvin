/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.io.loader;

import com.marvin.component.io.resource.ClassPathResource;
import com.marvin.component.io.IResource;
import com.marvin.component.util.Assert;

/**
 *
 * @author cdi305
 */
public class ClassPathResourceLoader extends DefaultResourceLoader {

    private final Class<?> clazz;

    /**
     * Create a new ClassRelativeResourceLoader for the given class.
     *
     * @param clazz the class to load resources through
     */
    public ClassPathResourceLoader(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        this.clazz = clazz;
        setClassLoader(clazz.getClassLoader());
    }

    @Override
    protected IResource getResourceByPath(String path) {
        return new ClassPathResource(path, this.clazz);
    }
}
