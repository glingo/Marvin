package com.marvin.component.kernel.bundle;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.container.extension.ExtensionInterface;
import com.marvin.component.util.ClassUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dr.Who
 */
public abstract class Bundle extends ContainerAware {
    
    protected ExtensionInterface extension;
    
    /**
     * Boots the Bundle.
     * @return Bundle
     */
    public Bundle boot(){
//        System.out.format("bundle is booting %s\n", this);
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
    public String getNamespace()
    {
        return this.getClass().getPackage().getName();
    }
    
    /**
     * Gets the Bundle directory path.
     *
     * @return string The Bundle absolute path
     */
    public String getName()
    {
        return this.getClass().getSimpleName().replace("Bundle", "");
    }
    
    /**
     * Gets the Bundle directory path.
     *
     * @return string The Bundle absolute path
     */
    public String getPath()
    {
        return this.getClass().getName();
    }
    
    /**
     * Returns the bundle's container extension class.
     *
     * @return string
     */
    protected String getContainerExtensionPath()
    {
        return String.format("%s.container.%sExtension", this.getNamespace(), this.getName());
    }
    
    protected Class getContainerExtensionClass(){
        String className = this.getContainerExtensionPath();
        try {
            return ClassUtils.forName(className, null);
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    protected ExtensionInterface createContainerExtension(){
        Class cl = this.getContainerExtensionClass();
        
        if(cl != null) {
            try {
                return (ExtensionInterface) cl.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ExtensionInterface getContainerExtension(){
        if(null == this.extension) {
            this.extension = this.createContainerExtension();
        }
        
        return this.extension;
    }
}
