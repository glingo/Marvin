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
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    private Container container;
    
    public ServiceFactory(Container container) {
        this.container = container;
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
            String msg = String.format("We could not instatiate the service %s.", id);
            throw new IllegalArgumentException(msg);
        }

        // apply post instanciation
        postInstanciate(id, definition, service);

        this.container.set(id, service);

        return service;
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
    
    private Constructor<Object> resolveConstructor(String className, Object[] arguments) {
        Class type = ClassUtils.resolveClassName(className, null);
        Class[] argumentsTypes = ReflectionUtils.resolveArgumentsTypes(arguments, null);
        
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
            Class[] types = ReflectionUtils.resolveArgumentsTypes(arguments, wanted);
            
            return ClassUtils.hasConstructor(type, types);
        }).findFirst().orElse(null);
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
    
    private Object callConstructor(String id, Definition definition, Object[] arguments) {
        Constructor<Object> constructor = resolveConstructor(definition.getClassName(), arguments);
        
        try {
            return constructor.newInstance(arguments);
        } catch (Exception exception) {
            throw new IllegalArgumentException(String.format("We could not find constructor for %s", id), exception);
        }
    }
    
    private void postInstanciate(String id, Definition definition, Object service) {
        if (service == null) {
            return;
        }
            
        applyAwareness(service);

        // call any registered methods if there is
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
            
            args.stream()
                .map(this::resolveArguments)
                .forEach((a) -> {
                    Class[] parameters = ReflectionUtils.resolveArgumentsTypes(a);
                    Method call = ReflectionUtils.findMethod(service.getClass(), name, parameters);
                    if (call == null) {
                        throw new IllegalArgumentException(String.format("No method found for %s (%s) (%s)", name, Arrays.toString(parameters), Arrays.toString(a)));
                    }
                    applyCall(call, service, a);
                });
        });
    }
    
    private void applyCall(Method call, Object service, Object[] args) {
        ReflectionUtils.invokeMethod(call, service, args);
    }
}
