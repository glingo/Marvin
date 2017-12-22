package com.marvin.component.translation;

import java.util.Locale;

public class TestTranslator {

    public static void main(String[] args) {
//        test\\com\\marvin\\component\\translation\\
        Translator translator = new Translator();
        translator.addResource("com.marvin.component.translation.test");
        translator.addResource("com.marvin.component.translation.progress");
//        translator.addResource(Locale.ENGLISH, "com.marvin.component.translation.test");
//        translator.addResource(Locale.ENGLISH, "com.marvin.component.translation.progress");

        testDefaultLocale(translator);
        test(translator, Locale.getAvailableLocales());
    }
    
    public static void testDefaultLocale(Translator translator){
        Locale locale = translator.getLocale();
        
        System.out.println(" With default locale : ");
        test(translator, locale);
    }
    
    public static void test(Translator translator, Locale locale) {
        String great = translator.trans(locale, "marvin.is.great");
        String progress = translator.trans(locale, "marvin.is.in.progress");
        
        StringBuilder builder = new StringBuilder();
        
        builder
                .append(" --- (")
                .append(locale)
                .append(") --- ")
                .append("\n\tgreat (")
                .append(locale)
                .append(") : ")
                .append(great)
                .append("\n\tprogress (")
                .append(locale)
                .append(") : ")
                .append(progress);
        
        System.out.println(builder.toString());
    }
    
    public static void test(Translator translator, Locale[] locales) {
        for (Locale locale : locales) {
            test(translator, locale);
        }
    }

}
