package com.marvin.old.locator;

import java.io.File;

/**
 *
 * @author Dr.Who
 */
public class FileLocator extends Locator<File> {
    
    @Override
    public File locate(String name) {
        File file = new File(name);
        
        if(!file.exists()) {
            return null;
        }
        
        return file;
    }
    
}
