package com.marvin.old.util.properties;

import java.util.Map;

import com.marvin.old.util.properties.strategy.LoaderStrategy;

public interface IPropertieLoader {

    public void load(String name, ClassLoader loader, LoaderStrategy strategy);

    public void load(String name, LoaderStrategy strategy);

    public int countProperties();

    public void clearProperties();

    public String getPropertie(String name);

    public Map<String, String> getPropertiesStartingWith(String start);

}
