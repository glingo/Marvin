package com.marvin.old.util.properties.strategy;

import com.marvin.old.util.properties.PropertieLoader;

public interface LoaderStrategy {

    void load(String name, ClassLoader loader, PropertieLoader builder);

}
