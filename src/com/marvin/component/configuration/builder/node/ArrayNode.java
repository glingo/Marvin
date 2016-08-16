package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.PrototypeNodeInterface;
import com.marvin.component.util.ObjectUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayNode extends Node implements PrototypeNodeInterface {

    protected boolean addIfNotSet = false;
    protected boolean normalizeKeys = true;
    protected boolean removeExtraKeys = true;
    protected HashMap<String, String> xmlRemapping = new HashMap<>();
    
    public ArrayNode(String name) {
        super(name);
    }

    @Override
    protected Object preNormalize(Object value) {
        
        if(!this.normalizeKeys || !(value instanceof Map)) {
            return value;
        }
        
        Map<String, Object> normalized = new HashMap();
        
        Map<String, Object> typedValue = (Map<String, Object>) value;
        typedValue.forEach((String k, Object val) -> {
            
            if(k.contains("-") && !k.contains("_")) {
                String normalizedKey = k.replace("-", "_");
                if(!typedValue.containsKey(normalizedKey)) {
                    k = normalizedKey;
                }
            }
            
            normalized.put(k, val);
        });
        
        return super.preNormalize(value); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void validateType(Object value) throws Exception {
        if(!ObjectUtils.isArray(value) 
                && !(value instanceof Map) 
                && !(value instanceof Set) 
                && !(value instanceof List)) {
            String msg = String.format("Invalid type for path %s. Expected array but got %s", this.getPath(), value == null ? null: value.getClass());
            throw new Exception(msg);
        }
    }

    @Override
    protected Object normalizeValue(Object value) {
        
        HashMap<String, Object> casted = (HashMap<String, Object>) value;
        casted = this.remapXml(casted);
        
        HashMap<String, Object> normalized = new HashMap<>();
        
        casted.forEach((String key, Object val) -> {
            if(this.children.containsKey(key)) {
                normalized.put(key, this.children.get(key).normalizeValue(val));
            } else if (!this.removeExtraKeys) {
                normalized.put(key, val);
            }
        });
        
        return normalized;
    }

    @Override
    protected Object mergeValues(Object left, Object right) {
        System.out.println("merge values " + left + " / " + right);
        return left;
    }

    @Override
    protected Object finalizeValue(Object value) throws Exception {
        HashMap<String, Object> casted = (HashMap<String, Object>) value;
        
        for(Map.Entry<String, Node> entry : this.children.entrySet()) {
            String key = entry.getKey();
            Node child = entry.getValue();
            
            if(!casted.containsKey(key)) {
                if(child.isRequired()) {
                    String msg = String.format("The child node '%s' at path '%s' must be configured.", key, this.getPath());
                    throw new Exception(msg);
                }
                
                if(child.hasDefaultValue()) {
                    casted.put(key, child.getDefaultValue());
                }
                
                continue;
            }
            
            casted.put(key, child.finalize(casted.get(key)));
        }
        
        return casted;
    }
    
    protected HashMap<String, Object> remapXml(HashMap<String, Object> value){
        this.xmlRemapping.forEach((String singular, String plural) -> {
            if(value.containsKey(singular)) {
                value.put(plural, value.get(singular));
                value.remove(singular);
            }
        });
        
        return value;
    }
    
    @Override
    public boolean hasDefaultValue() {
        return this.addIfNotSet;
    }

    @Override
    public Object getDefaultValue() {
       
        HashMap<String, Object> defaults = new HashMap<>();
        
        this.children.forEach((String key, Node child) -> {
            if(child.hasDefaultValue()) {
                defaults.put(key, child.getDefaultValue());
            }
        });
        
        return defaults;
    }

    public void setNormalizeKeys(boolean normalizeKeys) {
        this.normalizeKeys = normalizeKeys;
    }

    public void setXmlRemapping(HashMap<String, String> xmlRemapping) {
        this.xmlRemapping = xmlRemapping;
    }

    public HashMap<String, String> getXmlRemapping() {
        return xmlRemapping;
    }
}
