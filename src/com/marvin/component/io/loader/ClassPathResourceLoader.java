package com.marvin.component.io.loader;

import com.marvin.component.io.resource.ClassPathResource;
import com.marvin.component.util.Assert;

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
    protected ClassPathResource getResourceByPath(String path) {
        return new ClassPathResource(path, this.clazz);
    }
}
