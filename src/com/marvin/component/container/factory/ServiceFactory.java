package com.marvin.component.container.factory;

import com.marvin.component.container.Container;
import com.marvin.component.container.awareness.ContainerAwareInterface;
import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Parameter;
import com.marvin.component.container.config.Reference;
import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.ObjectUtils;
import com.marvin.component.util.ReflectionUtils;
import com.marvin.component.util.StringUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ServiceFactory {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    private Container container;
    
    public ServiceFactory(Container container) {
        this.container = container;
    }
    
    private Object resolveArgument(Object arg) {
        if (arg instanceof Reference) {
            arg = container.get(((Reference) arg).getTarget());
        }

        if (arg instanceof Parameter) {
            arg = container.getParameter(((Parameter) arg).getKey());
        }

        // recurse
        if (arg instanceof Collection) {
            arg = ((Collection) arg).stream().map(this::resolveArgument)
                    .collect(Collectors.toCollection(HashSet::new));
        }

        // recurse
        if (ObjectUtils.isArray(arg)) {
            arg = resolveArguments((Object[]) arg);
        }

        return arg;
    }
    
    private Object[] resolveArguments(Object[] arguments) {
        return Arrays.stream(arguments).map(this::resolveArgument).toArray();
    }
    
    private Class resolveArgumentType(Object argument, Class given) {
        if(null == argument) {
            return given;
        }
        
        if(null != given && ClassUtils.isAssignable(given, argument.getClass())) {
            return given;
        }
        
        return argument.getClass();
    }
    
    private Class[] resolveArgumentsTypes(Object[] arguments, Class[] given) {
        if(null == arguments) {
            return new Class[]{};
        }
        
        int len = arguments.length;
        int givenLength = 0;
        
        if(given != null) {
            givenLength = given.length;
        }
        
        Class[] types = new Class[len];
        for (int i = 0; i < len; i++) {
            Class wanted = null;
            
            if(i < givenLength) {
                wanted = given[i];
            }
            
            types[i] = resolveArgumentType(arguments[i], wanted);
        }
        
        return types;
    }
    
    private Constructor<Object> resolveConstructor(String className, Object[] arguments) {
        Class type = ClassUtils.resolveClassName(className, null);
        Class[] argumentsTypes = resolveArgumentsTypes(arguments, null);
        
        if(ClassUtils.hasConstructor(type, argumentsTypes)) {
            return ClassUtils.getConstructorIfAvailable(type, argumentsTypes);
        }

        int len = arguments.length;
        Constructor<Object>[] constructors = type.getConstructors();

        return Arrays.stream(constructors).filter((Constructor<Object> cstr) -> {
            if(len != cstr.getParameterCount()) {
                return false;
            }
            
            Class[] wanted = cstr.getParameterTypes();
            Class[] types = resolveArgumentsTypes(arguments, wanted);
            
            return ClassUtils.hasConstructor(type, types);
        }).findFirst().orElse(null);
    }
    
    public Object instanciate(String id, Definition definition) {
        Object service = null;
        
        // resolve arguments
        Object[] arguments = resolveArguments(definition.getArguments());

        try{
            // if we have a factory use it to build the service.
            if (StringUtils.hasLength(definition.getFactoryName())) {
                service = callFactory(id, definition, arguments);
            } else {
                service = callConstructor(id, definition, arguments);
            }
        } catch(Exception ex) {
            String msg = String.format("InstantiationException, we could not instatiate the service %s.", id);
            this.logger.severe(msg);
        }

        // apply post instanciation
        postInstanciate(id, definition, service);

        this.container.set(id, service);

        return service;
    }
    
    private Object callFactory(String id, Definition definition, Object[] arguments) throws Exception {
        // assume that args are resolved !
        
        Class factory = ClassUtils.resolveClassName(definition.getFactoryName(), null);
        
        //try to work with static methods
        
//        Method method = ClassUtils.getStatitcMethod(factory, definition.getFactoryMethodName(), new Class[]{});
//        ReflectionUtils.invokeMethod(method, null, arguments);
        
        Method method = ClassUtils.getMethod(factory, definition.getFactoryMethodName(), new Class[]{});
        return ReflectionUtils.invokeMethod(method, factory.newInstance(), arguments);
    }
    
    private Object callConstructor(String id, Definition definition, Object[] arguments) throws Exception {
        Constructor<Object> constructor = resolveConstructor(definition.getClassName(), arguments);
        if(null == constructor) {
            throw new Exception(String.format("We could not find constructor for %s", id));
        }
        return constructor.newInstance(arguments);
    }
    
    private void postInstanciate(String id, Definition definition, Object service) {
        if (service == null) {
            return;
        }
            
        applyAwareness(service);

        // call methods if there is
        applyCalls(id, definition, service);
    }
    
     private void applyAwareness(Object service){
        if (service instanceof ContainerAwareInterface) {
            ((ContainerAwareInterface) service).setContainer(container);
        }
    }
    
    private void applyCalls(String id, Definition definition, Object service) {
        if(!definition.hasCall()) {
            return;
        }
        
        definition.getCalls().entrySet().stream().forEach((entrySet) -> {
            String name = entrySet.getKey();
            List<Object[]> args = entrySet.getValue();

            args.stream().forEach((arg) -> {
                Method call = ReflectionUtils.findMethod(service.getClass(), name, (Class<?>[]) null);
                ReflectionUtils.invokeMethod(call, service, resolveArguments(arg));
            });
        });
    }
    
}
