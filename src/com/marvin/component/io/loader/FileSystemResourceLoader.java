package com.marvin.component.io.loader;

import com.marvin.component.io.resource.FileSystemResource;
import com.marvin.component.io.IResource;

public class FileSystemResourceLoader extends DefaultResourceLoader {

    /**
     * Resolve resource paths as file system paths.
     * <p>
     * Note: Even if a given path starts with a slash, it will get interpreted
     * as relative to the current VM working directory.
     *
     * @param path the path to the resource
     * @return the corresponding Resource handle
     * @see FileSystemResource
     * @see
     * org.springframework.web.context.support.ServletContextResourceLoader#getResourceByPath
     */
    @Override
    protected IResource getResourceByPath(String path) {
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        return new FileSystemResource(path);
    }
}
