package com.marvin.old.util.properties.strategy;

import java.io.IOException;
import java.io.InputStream;

import com.marvin.old.util.properties.PropertieLoader;

public class XMLLoaderStrategy implements LoaderStrategy {

    public void load(String name, ClassLoader loader, PropertieLoader builder) {
        try {
            InputStream stream = loader.getResourceAsStream(name);
            builder.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
