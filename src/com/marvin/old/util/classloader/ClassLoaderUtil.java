package com.marvin.old.util.classloader;

/*
 * Copyright 2002-2003,2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import com.marvin.old.util.classloader.iterator.AggregateIterator;

/**
 * This class is extremely useful for loading resources and classes in a fault
 * tolerant manner that works across different applications servers.
 * 
 * It has come out of many months of frustrating use of multiple application
 * servers at Atlassian, please don't change things unless you're sure they're
 * not going to break in one server or another!
 *
 */
public class ClassLoaderUtil {

    /**
     * Load all resources with a given name, potentially aggregating all results
     * from the searched classloaders. If no results are found, the resource
     * name is prepended by '/' and tried again.
     *
     * This method will try to load the resources using the following methods
     * (in order):
     * <ul>
     * <li>From Thread.currentThread().getContextClassLoader()</li>
     * <li>From ClassLoaderUtil.class.getClassLoader()</li>
     * <li>callingClass.getClassLoader()</li>
     * </ul>
     *
     * @param resourceName The name of the resources to load
     * @param callingClass The Class object of the calling object
     * @param aggregate
     * @return
     * @throws java.io.IOException
     */
    public static Iterator<URL> getResources(String resourceName, Class<?> callingClass, boolean aggregate) throws IOException {

        AggregateIterator<URL> iterator = new AggregateIterator<>();

        iterator.addEnumeration(Thread.currentThread().getContextClassLoader().getResources(resourceName));

        if (!iterator.hasNext() || aggregate) {
            iterator.addEnumeration(ClassLoaderUtil.class.getClassLoader().getResources(resourceName));
        }

        if (!iterator.hasNext() || aggregate) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                iterator.addEnumeration(cl.getResources(resourceName));
            }
        }

        if (!iterator.hasNext() && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) {
            return getResources('/' + resourceName, callingClass, aggregate);
        }

        return iterator;
    }

    public static Iterator<URL> getResources(String resourceName, boolean aggregate) throws IOException {
        return getResources(resourceName, null, aggregate);
    }

    /**
     * Load a given resource.
     * <p/>
     * This method will try to load the resource using the following methods (in
     * order):
     * <ul>
     * <li>From
     * {@link Thread#getContextClassLoader() Thread.currentThread().getContextClassLoader()}
     * <li>From
     * {@link Class#getClassLoader() ClassLoaderUtil.class.getClassLoader()}
     * <li>From the {@link Class#getClassLoader() callingClass.getClassLoader()
     * }
     * </ul>
     *
     * @param resourceName The name of the resource to load
     * @param callingClass The Class object of the calling object
     */
    public static URL getResource(String resourceName, Class<?> callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = ClassLoaderUtil.class.getClassLoader().getResource(resourceName);
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if (url == null) {
            url = callingClass.getResource(resourceName);
        }

        if ((url == null) && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) {
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

    public static URL getResource(String resourceName) {
        return getResource(resourceName, null);
    }

    public static File getResourceAsFile(String resourceName, Class<?> callingClass) {
        URL url = getResource(resourceName, callingClass);
        return new File(url.getPath());
    }

    /**
     * This is a convenience method to load a resource as a stream.
     *
     * The algorithm used to find the resource is given in getResource()
     *
     * @param resourceName The name of the resource to load
     * @param callingClass The Class<?> object of the calling object
     * @return
     */
    public static InputStream getResourceAsStream(String resourceName, Class<?> callingClass) {
        URL url = getResource(resourceName, callingClass);

        try {
            return (url != null) ? url.openStream() : null;
        } catch (IOException e) {
            return null;
        }
    }

    public static InputStream getResourceAsStream(String resourceName) {
        return getResourceAsStream(resourceName, null);
    }

    /**
     * Load a class with a given name.
     * <p/>
     * It will try to load the class in the following order:
     * <ul>
     * <li>From
     * {@link Thread#getContextClassLoader() Thread.currentThread().getContextClassLoader()}
     * <li>Using the basic {@link Class#forName(java.lang.String) }
     * <li>From
     * {@link Class#getClassLoader() ClassLoaderUtil.class.getClassLoader()}
     * <li>From the {@link Class#getClassLoader() callingClass.getClassLoader()
     * }
     * </ul>
     *
     * @param className The name of the class to load
     * @param callingClass The Class object of the calling object
     * @return
     */
    public static Class<?> loadClass(String className, Class<?> callingClass) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                try {
                    return ClassLoaderUtil.class.getClassLoader().loadClass(className);
                } catch (ClassNotFoundException exc) {
                    try {
                        return callingClass.getClassLoader().loadClass(className);
                    } catch (Exception other) {
                        return null;
                    }
                }
            }
        }
    }

    public static Class<?> loadClass(String className) {
        return loadClass(className, null);
    }

    /**
     * Prints the current classloader hierarchy - useful for debugging.
     */
    public static void printClassLoader() {
        System.out.println("ClassLoaderUtils.printClassLoader");
        printClassLoader(Thread.currentThread().getContextClassLoader());
    }

    /**
     * Prints the classloader hierarchy from a given classloader - useful for
     * debugging.
     * @param cl
     */
    public static void printClassLoader(ClassLoader cl) {
        System.out.println("ClassLoaderUtils.printClassLoader(cl = " + cl + ")");

        if (cl != null) {
            printClassLoader(cl.getParent());
        }
    }

}
