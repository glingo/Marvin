package com.marvin.component.container;

import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.DefinitionBuilderInterface;
import java.util.List;

public interface ContainerBuilderInterface {
    
    
//    default ArgumentBuilder define(String name) {
//        return arguments -> new Definition("default", name, arguments);
//    }
//    
//    default ContainerBuilderInterface define() {
//        return scope -> name -> arguments -> new Definition(scope, name, arguments);
//    }
    
//    default DefinitionBuilderInterface define(String name, List<Object> args) {
//        return new Definition("default", name, arguments);
//    }
    
//    public static ArgumentBuilder definition(String name) {
//        return arguments -> new Definition("default", name, arguments);
//    }
    
//    public static DefinitionBuilderInterface definition() {
//        return className -> arguments -> new Definition(className);
//    }
    
    public static interface DefinitionBuilderInterface {
        ContainerBuilderInterface and();
        
//        default ArgumentBuilder define(String name) {
//            return arguments -> new Definition("default", name, arguments);
//        }
//        default ArgumentBuilder definition() {
//            return name -> arguments -> new Definition("default", name, arguments);
//        }
    }
    
    public static interface ScopeBuilder {
        ClassNameBuilder scope(String scope);
    }
    
    public static interface ClassNameBuilder {
        ArgumentBuilder name(String name);
        
//        default ArgumentBuilder define(String name) {
//            return arguments -> new Definition("default", name, arguments);
//        }
    }
    
    public static interface ArgumentBuilder {
        ContainerBuilderInterface withArgs(Object... args);
        
        default ContainerBuilderInterface withArgs(List<Object> args) {
            return withArgs(args.toArray());
        }
    }
}
