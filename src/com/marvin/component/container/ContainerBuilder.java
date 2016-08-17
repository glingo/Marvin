package com.marvin.component.container;

import com.marvin.component.container.awareness.ContainerAwareInterface;
import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Parameter;
import com.marvin.component.container.config.Reference;
import com.marvin.component.container.extension.ExtensionInterface;
import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.ObjectUtils;
import com.marvin.component.util.ReflectionUtils;
import com.marvin.component.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author cdi305
 */
public class ContainerBuilder {

    private static final Logger LOG = Logger.getLogger(ContainerBuilder.class.getName());

    /**
     * The map where we stack Definitions.
     */
    protected ConcurrentMap<String, Definition> definitions;
    
    protected HashMap<String, ExtensionInterface> extensions;

    protected HashMap<String, Object> extensionConfigs;
    
    /**
     * The Container to build
     */
    protected Container container;

    public ContainerBuilder() {
        this.extensionConfigs = new HashMap<>();
        this.definitions = new ConcurrentHashMap<>();
        this.extensions = new HashMap<>();
        this.container = new Container();
    }

    public Container build() {
        this.definitions.keySet().forEach(this::get);
        
        this.extensions.values().forEach((ExtensionInterface extension) -> {
//            HashMap conf = this.extensionConfigs.get(extension.getAlias());
//            extension.load(conf, this);
            extension.load(this.extensionConfigs, this);
        });
        
        return this.container;
    }

    private Object resolveArgument(Object arg) {

        if (arg instanceof Reference) {
            Reference ref = (Reference) arg;
            arg = get(ref.getTarget());
        }

        if (arg instanceof Parameter) {
            Parameter parameter = (Parameter) arg;
            arg = getParameter(parameter.getKey());
        }

        if (arg instanceof Collection) {
            Collection collection = (Collection) arg;
            arg = collection.stream()
                    .map(this::resolveArgument)
                    .collect(Collectors.toCollection(HashSet::new));
        }

        if (ObjectUtils.isArray(arg)) {
            Object[] array = (Object[]) arg;
            arg = Arrays.stream(array)
                    .map(this::resolveArgument)
                    .collect(Collectors.toList()).toArray();
        }

        return arg;
    }

    private Object[] resolveArguments(Object[] arguments) {
        return Arrays.stream(arguments).map(this::resolveArgument).toArray();
    }

    private Constructor<Object> resolveConstructor(Definition definition) {
        try {
            Class type = Class.forName(definition.getClassName());

            int len = definition.getArguments().length;
            Constructor<Object>[] constructors = type.getConstructors();

            Predicate<Constructor<Object>> filter = (Constructor<Object> cstr) -> {
                return len == cstr.getParameterCount();
            };

            return Arrays.stream(constructors).filter(filter).findFirst().orElse(null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContainerBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Object instanciate(String id, Definition definition) {
        Object service = null;

        try {
            // if we have a factory use it to build the service.
            if (StringUtils.hasLength(definition.getFactoryName())) {
                Class factory = ClassUtils.resolveClassName(definition.getFactoryName(), null);
                Method method = ClassUtils.getMethod(factory, definition.getFactoryMethodName(), new Class[]{});
                service = ReflectionUtils.invokeMethod(method, factory.newInstance());

            } else {

                // resolution des arguments
                Object[] arguments = resolveArguments(definition.getArguments());
                Constructor<Object> constructor = resolveConstructor(definition);

                // instatiation
                if (constructor != null) {
                    service = constructor.newInstance(arguments);
                }
            }

        } catch (InstantiationException ex) {
            LOG.log(Level.WARNING, "InstantiationException, we could not instatiate the service", ex);
        } catch (IllegalAccessException ex) {
            LOG.log(Level.WARNING, "IllegalAccessException, we can not access to the constructor", ex);
        } catch (IllegalArgumentException ex) {
            LOG.log(Level.WARNING, "IllegalArgumentException, Oops something's wrong in arguments", ex);
        } catch (InvocationTargetException ex) {
            LOG.log(Level.WARNING, "InvocationTargetException, Oops something's wrong in constructor invocation", ex);
        }

        if (service != null) {
            if (service instanceof ContainerAwareInterface) {
                ((ContainerAwareInterface) service).setContainer(container);
            }

            if(definition.hasCall()) {
                for (Map.Entry<String, Object[]> entrySet : definition.getCalls().entrySet()) {
                    String name = entrySet.getKey();
                    Object[] args = entrySet.getValue();
    //                Class[] types = new Class[]{};
    //                
    //                if(args != null){
    //                    for (Object arg : args) {
    //                        if(arg != null) {
    //                            types = ObjectUtils.addObjectToArray(types, arg.getClass());
    //                        } else {
    //                            types = ObjectUtils.addObjectToArray(types, Object.class);
    //                        }
    //                    }
    //                }
    //                Method call = ClassUtils.getMethod(service.getClass(), name, types);
                    Method call = ReflectionUtils.findMethod(service.getClass(), name, (Class<?>[]) null);
                    ReflectionUtils.invokeMethod(call, service, args);
                }
            }
            
            this.container.set(id, service);
        }
        
        return service;
    }

    public Object get(String id) {
        Object service = null;

        try {
            service = this.container.get(id);
        } catch (ContainerException ex) {
            // The service has not been instatiate yet, look into definition registry ?
            Definition def = this.definitions.get(id);
            if (def != null) {
                service = instanciate(id, def);
            }
        }

        return service;
    }
    
    public void merge(ContainerBuilder builder) {
        this.addDefinitions(builder.getDefinitions());
        this.addParameters(builder.getParameters());
        this.addAliases(builder.getAliases());
        this.addExtensions(builder.getExtensions());
    }
    
    public void loadFromExtension(String key, HashMap values) throws Exception{
        String ns = this.getExtension(key).getAlias();
        this.extensionConfigs.put(ns, values);
    }

    public HashMap<String, Definition> findTaggedDefinitions(String name){
        HashMap<String, Definition> tagged = new HashMap<>();
        
        this.definitions.forEach((String id, Definition definition) -> { 
            if(definition.hasTag(name)) {
                tagged.put(id, definition);
            }
        });
        
        return tagged;
    }
    
    public void set(String id, Object service) {
        this.container.set(id, service);
    }
    
    public void addAlias(String id, String alias){
        this.container.addAlias(id, alias);
    }
    
    public void addAliases(Map<String, String> aliases){
        aliases.forEach(this::addAlias);
    }
    
    public Map<String, String> getAliases(){
        return this.container.getAliases();
    }
    
    public void addDefinition(String id, Definition definition) {
        this.definitions.put(id, definition);
    }
    
    public void addDefinitions(Map<String, Definition> definitions) {
        definitions.forEach(this::addDefinition);
    }

    public void addParameter(String key, Object value) {
        this.container.setParameter(key, value);
    }
    
    public void addParameters(Map<String, Object> parameters) {
        parameters.forEach(this::addParameter);
    }
    
    public Map<String, Object> getParameters() {
        return this.getContainer().getParameters();
    }

    public Object getParameter(String key) {
        return this.container.getParameter(key, null);
    }
    
    public Object getParameter(String key, Object defaultValue) {
        return this.container.getParameter(key, defaultValue);
    }

    public Container getContainer() {
        return container;
    }

    public ConcurrentMap<String, Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ConcurrentMap<String, Definition> definitions) {
        this.definitions = definitions;
    }
    
    public Map<String, ExtensionInterface> getExtensions() {
        return this.extensions;
    }
    
    public ExtensionInterface getExtension(String name) throws Exception {
        if(!this.extensions.containsKey(name)) {
            String msg = String.format("Container extension '%s' is not registered", name);
            throw new Exception(msg);
        }
        return this.extensions.get(name);
    }
    
    public void addExtensions(Map<String, ExtensionInterface> extensions) {
        extensions.values().forEach(this::addExtension);
    }
    
    public void addExtension(ExtensionInterface extension) {
        this.extensions.putIfAbsent(extension.getAlias(), extension);
    }

    public void registerExtension(ExtensionInterface extension) {
        if(extension != null) {
            this.extensions.putIfAbsent(extension.getAlias(), extension);
        }
    }
    
    public static String underscore(String id){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return id.replaceAll(regex, replacement).toLowerCase();
    }

}
