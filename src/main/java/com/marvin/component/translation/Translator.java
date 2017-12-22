package com.marvin.component.translation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {
    
//    private ListResourceBundle resources;
    
    private Locale locale;
    private HashMap<Locale, List<ResourceBundle>> catalog;
    private List<String> resources;
    
    public Translator() {
        this.locale = Locale.getDefault();
    }

    public Translator(Locale locale) {
        this.locale = locale;
    }
    
//    public void loadResources(){
//        String country = this.locale.getCountry();
//        loadResources(country);
//    }
    
//    public void loadResources(String language){
//        Locale locale = new Locale(language);
//        loadResources(locale);
//    }
//    
//    public void loadResources(String language, ClassLoader loader){
//        loadResources(locale, loader);
//    }
    
    public void loadResources(Locale locale) {
        loadResources(locale, this.getClass().getClassLoader());
    }
    
    public void loadResources(Locale locale, ClassLoader loader) {
        
        if(this.resources == null) {
            this.resources = new ArrayList<>();
        }
        
//        if(!this.resources.containsKey(locale)) {
//            System.out.println(locale.getLanguage());
//            this.resources.put(locale, new ArrayList<>());
//        }

        this.resources.forEach((String resource) -> {
            addBundle(locale, ResourceBundle.getBundle(resource, locale, loader));
        });
    }
    
    private void addBundle(Locale locale, ResourceBundle bundle) {
        if(!this.catalog.containsKey(locale)) {
            this.catalog.put(locale, new ArrayList<>());
        }
        
        this.catalog.get(locale).add(bundle);
    }
    
    public void addResource(String resource) {
        if(this.resources == null) {
            this.resources = new ArrayList<>();
        }
        
//        if(!this.resources.containsKey(locale)) {
//            this.resources.put(locale, new ArrayList<>());
//        }
        
        this.resources.add(resource);
    }
    
//    public void addResource(String resource) {
//        addResource(this.locale, resource);
//    }

//    public void addResource(String language, String resource) {
//        Locale locale = new Locale(language);
//        addResource(locale, resource);
//    }
    
    public List<ResourceBundle> getBundles(Locale locale) {
        if(this.catalog == null) {
            this.catalog = new HashMap<>();
        }
        
        if(!this.catalog.containsKey(locale)) {
            loadResources(locale);
        }
        
        return this.catalog.get(locale);
    }
    
    public String trans(String key) {
        return trans(this.locale, key);
    }
    
    
    public String trans(String key, HashMap params) {
        return trans(key, params, this.locale);
    }
    
    private String trans(String key, HashMap params, ResourceBundle bundle) {
        String value = bundle.getString(key);
        
        Pattern pattern = Pattern.compile("(?<var>%.*%)");
        Matcher matcher = pattern.matcher(value);
        
        while (matcher.find()) {
            String quoted = matcher.group("var");
            String var = quoted.replaceAll("%", "");
            
            if(params.containsKey(var)) {
                String replacement = Objects.toString(params.get(var), "null");
                value = value.replaceAll(quoted, replacement);
            }
            
        }
        
        return value;
    }
    
    public String trans(String key, HashMap params, Locale locale) {
        List<ResourceBundle> bundles = getBundles(locale);
        
        if(bundles == null) {
            return key;
        }
        
        Optional<ResourceBundle> optional = bundles.stream().filter((bundle) -> {
            return bundle.containsKey(key);
        }).findFirst();
        
        if(!optional.isPresent()) {
            return null;
        }
        
        return trans(key, params, optional.get());
    }
    
    public String trans(Locale locale, String key) {
        return trans(key, new HashMap(), locale);
    }

    public Locale getLocale() {
        return locale;
    }
    
}
