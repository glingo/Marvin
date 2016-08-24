package com.marvin.component.io.loader;

import com.marvin.component.io.IResource;
import com.marvin.component.util.ResourceUtils;

public abstract class ResourceLoader {

    /** Pseudo URL prefix for loading from the class path: "classpath:" */
    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

    public abstract IResource load(String location);
}
