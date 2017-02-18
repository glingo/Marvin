package com.marvin.component.kernel.bundle;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.container.extension.ExtensionInterface;
import com.marvin.component.util.ClassUtils;

/**
 * This is the first class-citizen.
 * 
 * @author cailly
 */
public abstract class Bundle extends ContainerAware {

    private static final String EXTENSION_PATH = "%s.container.%sExtension";
    private static final String BUNDLE_SUFFIX  = "Bundle";
    
    protected ExtensionInterface extension;
    
    /**
     * Boots the Bundle.
     * @return Bundle
     */
    public Bundle boot(){
        return this;
    }
    
    /**
     * Build the Bundle.
     * @param builder
     */
    public void build(ContainerBuilder builder){
        
    }
    

    /**
     * Shutdowns the Bundle.
     */
    public void shutdown(){
    
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
        return getClass().getName();
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
            return null;
        }
    }
    
    public ExtensionInterface getContainerExtension() {
        if(null == this.extension) {
            this.extension = createContainerExtension();
        }
        
        return this.extension;
    }
    
    protected ExtensionInterface createContainerExtension() {
        Class cl = getContainerExtensionClass();
        
        if(cl != null) {
            try {
                return (ExtensionInterface) cl.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                this.logger.severe("Cannot instantiate extension.");
            }
        }
        
        return null;
    }
}
