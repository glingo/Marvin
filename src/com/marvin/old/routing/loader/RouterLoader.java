package com.marvin.old.routing.loader;

import com.marvin.old.builder.Builder;
import com.marvin.old.loader.DelegateLoader;
import com.marvin.old.locator.ILocator;
import com.marvin.old.routing.Route;
import com.marvin.old.routing.Router;
import java.io.File;

/**
 *
 * @author Dr.Who
 */
public class RouterLoader extends DelegateLoader<File, Router, Route> {

    public RouterLoader(ILocator<File> locator, Builder<Router, Route> builder) {
        super(locator, builder);
        this.delegate(new XmlFileLoader(locator, builder));
    }
}
