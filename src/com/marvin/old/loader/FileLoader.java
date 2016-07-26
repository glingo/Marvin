/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.loader;

import com.marvin.old.builder.Builder;
import com.marvin.old.locator.ILocator;
import java.io.File;

/**
 *
 * @author Dr.Who
 * @param <P>
 * @param <M>
 */
public abstract class FileLoader<P, M> extends Loader<File, P, M> {
    
    public FileLoader(ILocator<File> locator, Builder<P, M> builder) {
        super(locator, builder);
    }

    @Override
    public void load(String name) {
        File file = this.locator.locate(name);
        
        if(file == null || !file.exists()){
            return;
        }
        
        this.load(file);
    }

    @Override
    public abstract void load(File file);
    
}
