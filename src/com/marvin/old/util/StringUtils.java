package com.marvin.old.util;

import java.util.List;

public class StringUtils {

    public final static String FOLDER_BAR = "/";
    public final static String ESPACE = " ";
    public final static String TIRET = "-";
    public final static String COL_SEPARATOR = "|";
    public final static String NEWLINE = System.getProperty("line.separator");
    public final static String POINT = ".";

    public static String camelCase(String mot) {
        return camelCase(mot, ESPACE);
    }

    public static String camelCase(String mot, String split) {

        if (mot == null) {
            mot = "";
        }

        if (split == null) {
            split = "";
        }

        String retour = mot;

        String[] splitted = mot.split(split);
        if (splitted.length > 0) {
            retour = splitted[0];

            int limit = splitted.length, i = 1;
            for (; i < limit; i++) {
                String first = splitted[i].substring(0, 1);
                retour += splitted[i].replaceFirst(first, first.toUpperCase());
            }
        }

        return retour;
    }

    public static String append(String a, Object... b) {
        for (Object b1 : b) {
            a += toString(b1);
        }
        return a;
    }

    public static String toTableauASCII(List<String> header, List<Object> objets) {
        StringBuilder retour = new StringBuilder();
        if (header != null && !header.isEmpty()) {
            header.stream().forEach((head) -> {
                format(head, 10, TIRET);
            });
        }
        return retour.toString();
    }

    public static String join(String jointure, String... tab) {
        String retour = "";
        int len = tab.length;

        for (int i = 0; i < len; i++) {
            retour = append(retour, tab[i] + ((len > i + 1) ? jointure : ""));
        }

        return retour.trim();
    }

    public static String join(String jointure, Object... tab) {
        String retour = "";
        int len = tab.length;

        for (int i = 0; i < len; i++) {
            retour = append(retour, tab[i] + ((len > i + 1) ? jointure : ""));
        }

        return retour.trim();
    }

    public static String toString(Object what) {
        
        if (what == null) {
            return "null";
        }
        
        return what.toString().trim();
    }

    public static String format(Object what, int size, String complement) {
        String retour = toString(what);
        String[] lignes = retour.split(NEWLINE);
        if (lignes != null && lignes.length > 1) {
            int len = lignes.length;
            for (int i = 0; i < len; i++) {
                String ligne = lignes[i];
                ligne = format(ligne, size, complement);
                if (len > 1 && (i + 1 < len)) {
                    ligne += NEWLINE;
                }
            }
            retour = join("", lignes);
        } else if (lignes != null) {

            int nbComplement = size - retour.length(),
                    half = nbComplement / 2;
            for (int j = 0; j < half; j++) {
                retour = complement + retour + complement;
            }
            retour = retour.substring(0, size - 1);
        }
        return retour;
    }
}
