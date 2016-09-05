package com.marvin.component.routing.config;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {

//    private String controller;
    private String path;
    
    private HashMap<String, Object> defaults;
    private HashMap<String, Pattern> requirements;
    
    private boolean compiled = false;
    
    // compiled
    private String staticPrefix;
    private Pattern pattern;
    
    private final Pattern replacementPattern = Pattern.compile("\\{.*?\\}");

    public Route() {
        super();
    }
    
    public void compile(){
        if(this.compiled) {
            return;
        }
        Matcher matcher = replacementPattern.matcher(path);
//        String staticPart = path.split("\\{.*?\\}")[0];
//        ^[//]+[hello]+[//]+(\w+)$
        StringBuffer sb = new StringBuffer();
        sb.append("^");
        
        while (matcher.find()) {
            String key = matcher.group().replaceAll("[{}]", "");
            
//            if(getDefaults().containsKey(key)) {
//                String value = getDefaults().getOrDefault(key, key);
//                regex = regex.replace(matcher.group(), value);
//            }
 
            if(getRequirements().containsKey(key)){
                Pattern value = getRequirements().get(key);
                System.out.println("value detected: \n\t");
                System.out.println(value);
                if(value != null){
                    matcher.appendReplacement(sb, "(" + value.pattern() + ")");
                }
            }
        }
        
        matcher.appendTail(sb);
        sb.append("$");
        
        System.out.println(sb.toString());
        
//        this.pattern = Pattern.compile(sb.toString());

        String regex = sb.toString();
        regex = regex
                .replaceAll("/", "[//]");
        this.pattern = Pattern.compile(regex);
        
        this.compiled = true;
    }
 
    public String getStaticPrefix() {
        compile();
        
        return this.staticPrefix;
    }
    
    public Pattern getPattern() {
        compile();
        
        return this.pattern;
    }
    
    public void addDefault(String key, String value) {
        getDefaults().put(key, value);
    }

    public boolean hasDefault(String key) {
        return getDefaults().containsKey(key);
    }
    
    public void setDefaults(HashMap<String, Object> defaults) {
        this.defaults = defaults;
    }

    public HashMap<String, Object> getDefaults() {
        if(this.defaults == null){
            this.defaults = new HashMap<>();
        }
        return defaults;
    }
    
    public boolean hasRequirement(String key) {
        return getRequirements().containsKey(key);
    }
    
    public void addRequirement(String key, String requirement) {
        getRequirements().put(key, Pattern.compile(requirement));
    }
    
    public void setRequirements(HashMap<String, Pattern> requirements) {
        this.requirements = requirements;
    }

    public HashMap<String, Pattern> getRequirements() {
        if(this.requirements == null){
            this.requirements = new HashMap<>();
        }
        return requirements;
    }
    
    
//    public Route(String path, String controller) {
//        super();
//        this.path = path;
//        this.controller = controller;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("\n---")
            .append(super.toString())
            .append("---")
            .append("\n\t static prefix : \n\t\t")
            .append(getStaticPrefix())
            .append("\n\t pattern : \n\t\t")
            .append(getPattern())
            .append("\n\t defaults : \n\t\t")
            .append(getDefaults())
            .append("\n\t requierements : \n\t\t")
            .append(getRequirements())
            .append("\n");
        
        
        return sb.toString();
    }
    
//    public String getController() {
//        return this.controller;
//    }
//
//    public void setController(String controller) {
//        this.controller = controller;
//    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        
        Pattern p = Pattern.compile("\\{.*?\\}");
//        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one {animal} two {animal}s in the yard");
        
        HashMap<String, String> context = new HashMap<>();
        context.put("animal", "dog");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String key = m.group().replaceAll("[{}]", "");
            System.out.println(key);
            m.appendReplacement(sb, context.get(key));
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
