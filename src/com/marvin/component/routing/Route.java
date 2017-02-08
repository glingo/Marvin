package com.marvin.component.routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {

    private String path;
    
    private List<String> variableNames;
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
        
        Matcher matcher = this.replacementPattern.matcher(path);
        StringBuffer sb = new StringBuffer();
        sb.append("^");
        
        while (matcher.find()) {
            String key = matcher.group().replaceAll("[{}]", "");
            addVariableName(key);
            
            if(getRequirements().containsKey(key)){
                Pattern value = getRequirements().get(key);
                if(value != null){
                    matcher.appendReplacement(sb, "(?<" + key + ">" + value.pattern() + ")");
                }
            }
        }
        
        matcher.appendTail(sb);
        sb.append("$");
        
        String regex = sb.toString().replaceAll("[*]", ".*");
        this.pattern = Pattern.compile(regex);
        
        Logger.getGlobal().log(Level.INFO, "Pattern : {0}", this.pattern.pattern());
        
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
    
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getVariableNames() {
        if(this.variableNames == null) {
            this.variableNames = new ArrayList<>();
        }
        return variableNames;
    }

    public void setVariableNames(List<String> variableNames) {
        this.variableNames = variableNames;
    }
    
    public void addVariableName(String name) {
        getVariableNames().add(name);
    }
    
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        
//        sb
//            .append("\n---")
//            .append(super.toString())
//            .append("---")
//            .append("\n\t static prefix : \n\t\t")
//            .append(getStaticPrefix())
//            .append("\n\t pattern : \n\t\t")
//            .append(getPattern())
//            .append("\n\t defaults : \n\t\t")
//            .append(getDefaults())
//            .append("\n\t requierements : \n\t\t")
//            .append(getRequirements())
//            .append("\n");
//        
//        
//        return sb.toString();
//    }

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
