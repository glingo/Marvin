package com.marvin.component.configuration.builder.node;

import com.marvin.component.configuration.builder.PrototypeNodeInterface;
import com.marvin.component.util.ObjectUtils;
import java.util.HashMap;
import java.util.Map;

public class ArrayNode extends Node implements PrototypeNodeInterface {

    protected boolean addIfNotSet = false;
    protected boolean normalizeKeys = true;
    
    public ArrayNode(String name) {
        super(name);
    }
    
    public boolean hasDefaultValue() {
        return this.addIfNotSet;
    }

    public void setNormalizeKeys(boolean normalizeKeys) {
        this.normalizeKeys = normalizeKeys;
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
    protected void validateType(Object value) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object normalizeValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object mergeValues(Object left, Object right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object finalizeValue(Object value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
