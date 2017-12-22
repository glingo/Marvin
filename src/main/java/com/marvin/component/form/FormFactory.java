package com.marvin.component.form;

import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class FormFactory {
    
    private Map<String, Object> registry;

    public FormFactory(Map<String, Object> registry) {
        this.registry = registry;
    }

    public FormFactory() {
    }

    public Map<String, Object> getRegistry() {
        if(null == this.registry) {
            this.registry = new HashMap<>();
        }
        return this.registry;
    }

    public void setRegistry(Map<String, Object> registry) {
        this.registry = registry;
    }
    
    public boolean has(String key) {
        return getRegistry().containsKey(key);
    }
    
    public void add(String key, Object value) {
        if(null == value) {
            return;
        }
        
        if(!has(key)) {
            getRegistry().put(key, value);
        }
    }
    
    public FormTypeInterface load(String key, FormTypeInterface defaultType) throws Exception {
        Object form = getRegistry().getOrDefault(key, defaultType);
        
        if(form instanceof String) {
            Class<FormTypeInterface> type = (Class<FormTypeInterface>) ClassUtils.forName(form.toString(), null);
            Constructor<FormTypeInterface> constructor = ReflectionUtils.accessibleConstructor(type, new Class[0]);
            form = constructor.newInstance();
            
            getRegistry().put(key, form);
        }
        
        return (FormTypeInterface) form;
    }
    
//    public FormTypeInterface load(String key) throws Exception {
//        return load(key, new FormType());
//    }
    
    public FormTypeInterface createForm(String name, String type) throws Exception {
//        FormTypeInterface form = load(name);
        
//        return form;
        return null;
    }
    
}
