package com.marvin.old.util.serialize.strategy;

import java.io.IOException;
import java.io.InputStream;

import com.marvin.old.util.serialize.Loadable;

public interface ILoadStrategy {

    void load(InputStream str, Loadable loadable);

    void load(String s, boolean formBundle, Loadable loadable) throws IOException;

    void load(Class<?> c, String s, Loadable loadable) throws IOException;

}
