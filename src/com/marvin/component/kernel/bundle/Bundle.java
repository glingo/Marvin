/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.bundle;

/**
 *
 * @author Dr.Who
 */
public abstract class Bundle {
    
    /**
     * Boots the Bundle.
     * @return Bundle
     */
    public Bundle boot(){
//        System.out.format("bundle is booting %s\n", this);
        return this;
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
        return this.getClass().getCanonicalName();
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
        return String.format("%s.%sExtension.java", this.getNamespace(), this.getName());
    }
}
