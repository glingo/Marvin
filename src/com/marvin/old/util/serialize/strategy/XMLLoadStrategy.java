package com.marvin.old.util.serialize.strategy;

import java.beans.XMLDecoder;
import java.io.InputStream;

import com.marvin.old.util.serialize.Loadable;

public class XMLLoadStrategy extends LoadStrategy {

    @Override
    public void load(InputStream str, Loadable loadable) {
        // ouverture de decodeur
        XMLDecoder decoder = new XMLDecoder(str);
        try {
            // deserialisation de l'objet
            loadable = (Loadable) decoder.readObject();
        } finally {
            // fermeture du decodeur
            decoder.close();
        }
    }

}
