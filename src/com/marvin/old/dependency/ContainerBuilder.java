package com.marvin.old.dependency;

import com.marvin.old.builder.Builder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author Dr.Who
 */
public class ContainerBuilder extends Builder<Container, Definition> {
    
    protected static final Logger LOGGER = Logger.getLogger(ContainerBuilder.class.getName());

    public ContainerBuilder() {
        super();
    }
    
    @Override
    public Container create() {
        return new Container();
    }
    
//    protected void addDefinition(String id, Class type, Object[] arguments, boolean deprecated) {
//        this.resolveReferences(arguments);
//        Collector<Object, ?, List<Object>> argumentCollector = Collectors.mapping(this::resolveReferences, Collectors.toList());
//        
//    }

    @Override
    public void prepareMaterial(String id, Definition definition) {
        Constructor<Object> constructor;
        Class type = definition.getType();
        
        Collector<Object, ?, List<Object>> argumentCollector = Collectors.mapping(this::resolveReferences, Collectors.toList());
        Collector<Object, ?, List<Class>> typeCollector = Collectors.mapping(this::resolveType, Collectors.toList());
        
        List<Object> arguments = Arrays.asList(definition.getArguments());
        arguments = arguments.stream().collect(argumentCollector);
        Class[] types = arguments.stream().collect(typeCollector).toArray(new Class[]{});
        
        if(type == null) {
            return;
        }
        
        Constructor<Object>[] list = type.getConstructors();
        
        try {
            constructor = type.getConstructor(types);
        } catch (NoSuchMethodException ex) {
            constructor = Arrays.stream(list).filter((Constructor<Object> c) -> {
                return definition.getArguments().length == c.getParameterCount();
            }).findFirst().get();
        }
        
        if(constructor == null) {
            return;
        }
        
        try {
            Object service = constructor.newInstance(arguments.toArray());
            this.product.set(id, service);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ContainerBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Object resolveReferences(Object argument){
        if(argument instanceof Reference) {
            String id = ((Reference) argument).getId();
            argument = this.get(id);
        }
        return argument;
    }
    
    private Class resolveType(Object argument){
        
        if(argument instanceof Reference) {
            Definition def = this.materials.getOrDefault(((Reference) argument).getId(), null);
            
            if(def != null) {
                return def.getType();
            }
        }
        
        if(argument != null) {
            return argument.getClass();
        }
        
        return Object.class;
    }
    
    public Object get(String id) {
        try {
            Object service = this.product.get(id);
            return service;
        } catch (Exception ex) {
            Definition def = this.materials.getOrDefault(id, null);
            if(def != null) {
                this.prepareMaterial(id, def);
                return this.get(id);
            }
            Logger.getLogger(ContainerBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Constructor<Object> guess(String id, Class type, List<Class> types, List<Object> arguments){
        Constructor<Object>[] list = type.getConstructors();
        
        List<Constructor<Object>> proposables = Arrays.stream(list).filter((Constructor<Object> c) -> {
            return c.getParameterCount() == arguments.size();
        }).collect(Collectors.toList());
        
        return proposables.stream().findFirst().get();
    }

}
