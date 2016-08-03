package com.marvin.component.io.loader;

import com.marvin.component.io.resource.ClassPathResource;
import com.marvin.component.io.IResource;
import com.marvin.component.io.resource.UrlResource;
import com.marvin.component.util.Assert;
import com.marvin.component.util.ClassUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author cdi305
 */
public class DefaultResourceLoader extends ResourceLoader {

    private ClassLoader classLoader;

    private final Set<ProtocolResolver> protocolResolvers = new LinkedHashSet<>(4);

    /**
     * Create a new DefaultResourceLoader.
     * <p>
     * ClassLoader access will happen using the thread context class loader at
     * the time of this ResourceLoader's initialization.
     *
     * @see java.lang.Thread#getContextClassLoader()
     */
    public DefaultResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    /**
     * Create a new DefaultResourceLoader.
     *
     * @param classLoader the ClassLoader to load class path resources with, or
     * {@code null} for using the thread context class loader at the time of
     * actual resource access
     */
    public DefaultResourceLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * Specify the ClassLoader to load class path resources with, or
     * {@code null} for using the thread context class loader at the time of
     * actual resource access.
     * <p>
     * The default is that ClassLoader access will happen using the thread
     * context class loader at the time of this ResourceLoader's initialization.
     *
     * @param classLoader
     */
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * Return the ClassLoader to load class path resources with.
     * <p>
     * Will get passed to ClassPathResource's constructor for all
     * ClassPathResource objects created by this resource loader.
     *
     * @return
     * @see ClassPathResource
     */
    public ClassLoader getClassLoader() {
        return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
    }

    /**
     * Register the given resolver with this resource loader, allowing for
     * additional protocols to be handled.
     * <p>
     * Any such resolver will be invoked ahead of this loader's standard
     * resolution rules. It may therefore also override any default rules.
     *
     * @param resolver
     * @since 4.3
     * @see #getProtocolResolvers()
     */
    public void addProtocolResolver(ProtocolResolver resolver) {
        Assert.notNull(resolver, "ProtocolResolver must not be null");
        this.protocolResolvers.add(resolver);
    }

    /**
     * Return the collection of currently registered protocol resolvers,
     * allowing for introspection as well as modification.
     *
     * @return
     * @since 4.3
     */
    public Collection<ProtocolResolver> getProtocolResolvers() {
        return this.protocolResolvers;
    }

    @Override
    public IResource load(String location) {
        Assert.notNull(location, "Location must not be null");

        for (ProtocolResolver protocolResolver : this.protocolResolvers) {
            IResource resource = protocolResolver.resolve(location, this);
            if (resource != null) {
                return resource;
            }
        }

        if (location.startsWith("/")) {
            return getResourceByPath(location);
        } else if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), getClassLoader());
        } else {
            try {
                // Try to parse the location as a URL...
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException ex) {
                // No URL -> resolve as resource path.
                return getResourceByPath(location);
            }
        }
    }

    /**
     * Return a Resource handle for the resource at the given path.
     * <p>
     * The default implementation supports class path locations. This should be
     * appropriate for standalone implementations but can be overridden, e.g.
     * for implementations targeted at a Servlet container.
     *
     * @param path the path to the resource
     * @return the corresponding Resource handle
     * @see ClassPathResource
     */
    protected IResource getResourceByPath(String path) {
        return new ClassPathResource(path, getClassLoader());
    }
}
