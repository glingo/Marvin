package com.marvin.component.kernel.bundle;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.container.extension.ExtensionInterface;

import com.marvin.component.util.ClassUtils;
import java.util.logging.Level;

public abstract class Bundle extends ContainerAware {

    private static final String EXTENSION_PATH = "%s.container.%sExtension";
    private static final String BUNDLE_SUFFIX = "Bundle";

    protected ExtensionInterface extension;

    /**
     * Boots the Bundle.
     *
     * @return Bundle
     */
    public Bundle boot() {
        this.logger.log(Level.FINEST, "booting {}", this.getName());
        
        // real bundles will add specific tasks here
        
        return this;
    }

    /**
     * Build the Bundle.
     *
     * @param builder
     */
    public void build(ContainerBuilder builder) {
        this.logger.log(Level.FINEST, "building {}", this.getName());

        // real bundles will add specific tasks here
    }

    /**
     * Shutdowns the Bundle.
     */
    public void shutdown() {
        this.logger.log(Level.FINEST, "shuting down {}", this.getName());

    }

    /**
     * Gets the Bundle namespace.
     *
     * @return string The Bundle namespace
     */
    public String getNamespace() {
        return getClass().getPackage().getName();
    }

    /**
     * Gets the Bundle directory path.
     *
     * @return string The Bundle absolute path
     */
    public String getName() {
        return getClass().getSimpleName().replace(BUNDLE_SUFFIX, "");
    }

    /**
     * Gets the Bundle directory path.
     *
     * @return string The Bundle absolute path
     */
    public String getPath() {
//        return getClass().getPackage().replace(".", File.separator);
        return getNamespace().replace(".", "/");
    }

    
    public ExtensionInterface getContainerExtension() {
        if (null == this.extension) {
            this.extension = createContainerExtension();
        }

        return this.extension;
    }

    /**
     * Returns the bundle's container extension class.
     *
     * @return string
     */
    protected String getContainerExtensionPath() {
        return String.format(EXTENSION_PATH, getNamespace(), getName());
    }

    protected Class getContainerExtensionClass() {
        String className = getContainerExtensionPath();
        
        try {
            return ClassUtils.forName(className, null);
        } catch (ClassNotFoundException ex) {
            String msg = String.format("Cannot find extension %s.", className);
            this.logger.severe(msg);
        }
        
        return null;
    }

    protected ExtensionInterface createContainerExtension() {
        Class c = getContainerExtensionClass();
        
        try {
            if (c != null) {
                return (ExtensionInterface) c.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            String msg = String.format("Cannot instantiate extension %s.", c);
            this.logger.severe(msg);
        }
        
        return null;
    }
}
