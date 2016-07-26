package com.marvin.old.util.serialize.strategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.marvin.old.util.serialize.Loadable;

public abstract class LoadStrategy implements ILoadStrategy {

    protected void close(InputStream str) {
        try {
            if (str != null) {
                str.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(String s, boolean formBundle, Loadable loadable) {
        InputStream stream = null;
        try {
            if (formBundle) {
                stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(s);
            } else {
                stream = new FileInputStream(s);
            }

            if (stream == null) {
                throw new IOException(String.format("aucun fichier trouv� sous le nom %s", s));
            }

            load(stream, loadable);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stream);
        }
    }

    @Override
    public void load(Class<?> c, String s, Loadable loadable) {
        InputStream stream = null;
        try {
            URL url = c.getResource(s);

            if (url == null) {
                throw new IOException(String.format("aucun fichier trouv� sous le nom %s", s));
            }

            stream = url.openStream();

            if (stream == null) {
                throw new IOException(String.format("aucun fichier trouv� sous le nom %s", s));
            }

            load(stream, loadable);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stream);
        }
    }

    @Override
    public abstract void load(InputStream str, Loadable loadable);
}
