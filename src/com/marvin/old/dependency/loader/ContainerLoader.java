package com.marvin.old.dependency.loader;

import com.marvin.old.builder.Builder;
import com.marvin.old.dependency.Container;
import com.marvin.old.dependency.Definition;
import com.marvin.old.loader.DelegateLoader;
import com.marvin.old.locator.ILocator;
import java.io.File;

/**
 *
 * @author Dr.Who
 */
public class ContainerLoader extends DelegateLoader<File, Container, Definition> {
    
    public ContainerLoader(ILocator<File> locator, Builder<Container, Definition> builder) {
        super(locator, builder);
        this.delegate(new XmlFileLoader(locator, builder));
    }

}
