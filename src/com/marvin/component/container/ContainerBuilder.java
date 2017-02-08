package com.marvin.component.container;

import java.lang.reflect.Constructor;
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

import com.marvin.component.container.awareness.ContainerAwareInterface;
import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Parameter;
import com.marvin.component.container.config.Reference;
import com.marvin.component.container.extension.ExtensionInterface;
import com.marvin.component.container.compiler.Compiler;
import com.marvin.component.container.compiler.PassConfig;
import com.marvin.component.container.compiler.passes.CompilerPassInterface;
import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.ObjectUtils;
import com.marvin.component.util.ReflectionUtils;
import com.marvin.component.util.StringUtils;
import java.util.List;

public class ContainerBuilder {

    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    protected Compiler compiler;

    /** The map where we stack Definitions. */
    protected ConcurrentMap<String, Definition> definitions;
    
    protected Map<String, ExtensionInterface> extensions;

    protected Map<String, Map<String, Object>> extensionConfigs;
    
    /** The Container to build. */
    protected Container container;

    public ContainerBuilder() {
        
    }

    public Container build() {
        
        this.logger.info("Building a Container.");
        
        compile();
        
        getDefinitions().keySet().forEach(this::get);
        
        this.logger.info("Container has been built.");
        
        return getContainer();
    }
    
    private void compile(){
        this.logger.info("Compiling a Container.");
        getCompiler().compile(this);
        this.logger.info("Container is compiled.");
    }

    private Object resolveArgument(Object arg) {

        this.logger.info(String.format("Resolving an argument (%s) .", arg));
        
        if (arg instanceof Reference) {
            arg = get(((Reference) arg).getTarget());
        }

        if (arg instanceof Parameter) {
            arg = getParameter(((Parameter) arg).getKey());
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
        
        this.logger.info(String.format("Resolving an argument type (%s::%s) .", argument, given));
        
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
        
        this.logger.info(String.format("Resolving constructor %s(%s) .", 
                className, Arrays.toString(arguments)));
        
        Class type = ClassUtils.resolveClassName(className, null);
        
        Class[] argumentsTypes = resolveArgumentsTypes(arguments, null);
        
        if(ClassUtils.hasConstructor(type, argumentsTypes)) {
            // we got a contructor !
            return ClassUtils.getConstructorIfAvailable(type, argumentsTypes);
        }

        // we need to find it in an other way
        int len = arguments.length;
        Constructor<Object>[] constructors = type.getConstructors();

        Predicate<Constructor<Object>> filter = (Constructor<Object> cstr) -> {
            
            if(len != cstr.getParameterCount()) {
                return false;
            }
            
            Class[] wanted = cstr.getParameterTypes();
            Class[] types = resolveArgumentsTypes(arguments, wanted);
            
            return ClassUtils.hasConstructor(type, types);
        };

        return Arrays.stream(constructors).filter(filter).findFirst().orElse(null);
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
            this.logger.log(Level.WARNING, msg, ex);
        }

        // apply post instanciation
        postInstanciate(id, definition, service);

        set(id, service);

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
            ((ContainerAwareInterface) service).setContainer(getContainer());
        }
    }
    
    private void applyCalls(String id, Definition definition, Object service) {
        
        // do nothing
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
    
    /* Container methods */
    
    public Container getContainer() {
        
        if(this.container == null) {
            this.container = new Container();
        }
        
        return this.container;
    }

    public Object get(String id) {
        Object service = null;

        try {
            service = getContainer().get(id);
        } catch (ContainerException ex) {
            // The service has not been instatiate yet, look into definition registry ?
            if(hasDefinition(id)) {
                Definition def = getDefinition(id);
                service = instanciate(id, def);
            }
        }

        return service;
    }
    
    public void set(String id, Object service) {
        getContainer().set(id, service);
    }
    
    public void merge(ContainerBuilder builder) {
        addDefinitions(builder.getDefinitions());
        addParameters(builder.getParameters());
        addAliases(builder.getAliases());
        addExtensions(builder.getExtensions());
    }
    
    public void loadFromExtension(String key, Map values) {
        try {
            String ns = getExtension(key).getAlias();
            getExtensionConfigs().put(ns, values);
        } catch (Exception ex) {
            Logger.getLogger(ContainerBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, Definition> findTaggedDefinitions(String name){
        HashMap<String, Definition> tagged = new HashMap<>();
        
        getDefinitions().forEach((String id, Definition definition) -> { 
            if(definition.hasTag(name)) {
                tagged.put(id, definition);
            }
        });
        
        return tagged;
    }
    
    /* Aliases methods */
    
    public void addAlias(String id, String alias){
        getContainer().addAlias(id, alias);
    }
    
    public void addAliases(Map<String, String> aliases){
        
        if(aliases == null) {
            return;
        }
        
        aliases.forEach(this::addAlias);
    }
    
    public Map<String, String> getAliases(){
        return getContainer().getAliases();
    }
    
    /* Definitions methods */
    
    public boolean hasDefinition(String id) {
        return getDefinitions().containsKey(id);
    }
     
    public void addDefinition(String id, Definition definition) {
        getDefinitions().put(id, definition);
    }
    
    public void addDefinitions(Map<String, Definition> definitions) {
        if(definitions == null) {
            return;
        }
        definitions.forEach(this::addDefinition);
    }
    
    public Definition getDefinition(String id) {
        if(!hasDefinition(id)) {
            return null;
        }
        return getDefinitions().get(id);
    }
    
    public ConcurrentMap<String, Definition> getDefinitions() {
        
        if(this.definitions == null) {
            this.definitions = new ConcurrentHashMap<>();
        }
        
        return this.definitions;
    }

    public void setDefinitions(ConcurrentMap<String, Definition> definitions) {
        this.definitions = definitions;
    }

    
    /* Parameters methods */
    
    public void addParameter(String key, Object value) {
        getContainer().setParameter(key, value);
    }
    
    public void addParameters(Map<String, Object> parameters) {
        parameters.forEach(this::addParameter);
    }
    
    public Map<String, Object> getParameters() {
        return getContainer().getParameters();
    }
    
    public Object getParameter(String key) {
        return getParameterWithDefault(key, null);
    }
    
    public <T> T getParameter(String key, Class<T> type) {
        return (T) getParameter(key);
    }
    
    public Object getParameterWithDefault(String key, Object defaultValue) {
        return getContainer().getParameterWithDefault(key, defaultValue);
    }
    
    public <T> T getParameterWithDefault(String key, Object defaultValue, Class<T> type) {
        return getContainer().getParameterWithDefault(key, defaultValue, type);
    }
    
    /* Extensions methods */
    
    public Map<String, Object> getExtensionConfig(String name) {
        if(!getExtensionConfigs().containsKey(name)) {
            getExtensionConfigs().put(name, new HashMap());
        }
        
        return getExtensionConfigs().get(name);
    }
    
    public Map<String, Map<String, Object>> getExtensionConfigs() {
        if(this.extensionConfigs == null) {
            this.extensionConfigs = new HashMap<>();
        }
        
        return this.extensionConfigs;
    }
    
    public Map<String, ExtensionInterface> getExtensions() {
        
        if(this.extensions == null) {
            this.extensions = new HashMap<>();
        }
        
        return this.extensions;
    }
    
    public ExtensionInterface getExtension(String name) throws Exception {
        
        if(!getExtensions().containsKey(name)) {
            String msg = String.format("Container's extension '%s' is not registered", name);
            throw new Exception(msg);
        }
        
        return getExtensions().get(name);
    }
    
    public void addExtensions(Map<String, ExtensionInterface> extensions) {
        
        if(extensions == null) {
            return;
        }
        
        extensions.values().forEach(this::addExtension);
    }
    
    public void addExtension(ExtensionInterface extension) {
        
        if(extension == null) {
            return;
        }
        
        getExtensions().putIfAbsent(extension.getAlias(), extension);
        getExtensionConfigs().put(extension.getAlias(), new HashMap<>());
    }

    public void registerExtension(ExtensionInterface extension) {
        addExtension(extension);
    }
    
    /* Compiler methods */
    
    private Compiler getCompiler(){
        if(this.compiler == null) {
            this.compiler = new Compiler();
        }
        
        return this.compiler;
    }
    
    public void addCompilerPass(CompilerPassInterface pass) throws Exception {
        addCompilerPass(pass, PassConfig.BEFORE_OPTIMIZATION);
    }
    
    public void addCompilerPass(CompilerPassInterface pass, String type) {
        
        if(pass == null) {
            return;
        }
        
        try {
            getCompiler().getPassConfig().addPass(pass, type);
        } catch (Exception ex) {
            this.logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }        
    
    
    /* static methods */
    
    
    public static String underscore(String id){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return id.replaceAll(regex, replacement).toLowerCase();
    }

    /* others */
    
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("\n  ContainerBuilder \n");
//        
//        builder.append("\n----------------Liste des Extensions----------------");
//        
//        this.getExtensions().forEach((String id, Object extension) -> {
//            builder.append("\n").append(id).append(": ").append(extension);
//        });
//        
//        builder.append("\n----------------Liste des Definitions----------------");
//        this.getDefinitions().forEach((String id, Object definition) -> {
//            builder.append("\n").append(id).append(": ").append(definition);
//        });
//        
//        
//        builder.append("\n----------------Fin ContainerBuilder----------------");
//        
//        return builder.toString();
//    }
    
}
