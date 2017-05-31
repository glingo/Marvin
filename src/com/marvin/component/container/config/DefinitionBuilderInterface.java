package com.marvin.component.container.config;

import java.util.List;

public interface DefinitionBuilderInterface {
    
//    Definition definition();
    Definition name(String name);
    
//    Definition definition(String scope, String name);
//    Definition definition(String scope, String name, Object[] args);
    
    public static DefinitionBuilderInterface definition() {
        return className -> new Definition(className);
    }

//    default Definition definition(String name) {
//        return scope -> arguments -> new Definition(scope, className, arguments);
//    }
    
//    public static interface ScopeBuilder {
//        ClassNameBuilder scope(String scope);
//    }
    
//    public static interface ClassNameBuilder {
//        ArgumentBuilder className(String name);
//    }
    
//    public static interface ArgumentBuilder {
//        Definition arguments(Object... args);
//        
//        default Definition arguments(List<Object> args) {
//            return arguments(args.toArray());
//        }
//    }

}