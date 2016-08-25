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

    private static final Logger LOG = Logger.getLogger(ContainerBuilder.class.getName());
    
    protected Compiler compiler;

    /**
     * The map where we stack Definitions.
     */
    protected ConcurrentMap<String, Definition> definitions;
    
    protected HashMap<String, ExtensionInterface> extensions;

    protected HashMap<String, HashMap<String, Object>> extensionConfigs;
    
    /**
     * The Container to build
     */
    protected Container container;

    public ContainerBuilder() {
        
    }

    public Container build() {
        
        getCompiler().compile(this);
        
        getDefinitions().keySet().forEach(this::get);
        
        return getContainer();
    }

    private Object resolveArgument(Object arg) {

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
    
    private Constructor<Object> resolveConstructor(Definition definition) {

        Class type = ClassUtils.resolveClassName(definition.getClassName(), null);

        int len = definition.getArguments().length;
        Constructor<Object>[] constructors = type.getConstructors();

        Predicate<Constructor<Object>> filter = (Constructor<Object> cstr) -> {
            return len == cstr.getParameterCount();
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
            LOG.log(Level.WARNING, msg, ex);
        }

        // apply post instanciation
        postInstanciate(id, definition, service);

        set(id, service);

        return service;
    }
    
    private Object callFactory(String id, Definition definition, Object[] arguments) throws Exception {
        Class factory = ClassUtils.resolveClassName(definition.getFactoryName(), null);
        Method method = ClassUtils.getMethod(factory, definition.getFactoryMethodName(), new Class[]{});
        return ReflectionUtils.invokeMethod(method, factory.newInstance(), arguments);
    }
    
    private Object callConstructor(String id, Definition definition, Object[] arguments) throws Exception {
        Constructor<Object> constructor = resolveConstructor(definition);
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
    
    public void loadFromExtension(String key, HashMap values) throws Exception {
        String ns = getExtension(key).getAlias();
        getExtensionConfigs().put(ns, values);
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
        return getContainer().getParameter(key, null);
    }
    
    public Object getParameter(String key, Object defaultValue) {
        return getContainer().getParameter(key, defaultValue);
    }
    
    /* Extensions methods */
    
    public HashMap<String, Object> getExtensionConfig(String name) {
        if(!getExtensionConfigs().containsKey(name)) {
            getExtensionConfigs().put(name, new HashMap());
        }
        
        return getExtensionConfigs().get(name);
    }
    
    public HashMap<String, HashMap<String, Object>> getExtensionConfigs() {
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
        extensions.values().forEach(this::addExtension);
    }
    
    public void addExtension(ExtensionInterface extension) {
        getExtensions().putIfAbsent(extension.getAlias(), extension);
    }

    public void registerExtension(ExtensionInterface extension) {
        if(extension != null) {
            getExtensions().putIfAbsent(extension.getAlias(), extension);
            getExtensionConfigs().put(extension.getAlias(), new HashMap<>());
        }
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
    
    public void addCompilerPass(CompilerPassInterface pass, String type) throws Exception {
        getCompiler().getPassConfig().addPass(pass, type);
    }
    
    
    /* static methods */
    
    
    public static String underscore(String id){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return id.replaceAll(regex, replacement).toLowerCase();
    }

    /* others */
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n  ContainerBuilder \n");
        
        builder.append("\n----------------Liste des Extensions----------------");
        
        this.getExtensions().forEach((String id, Object extension) -> {
            builder.append("\n").append(id).append(": ").append(extension);
        });
        
        builder.append("\n----------------Liste des Definitions----------------");
        this.getDefinitions().forEach((String id, Object definition) -> {
            builder.append("\n").append(id).append(": ").append(definition);
        });
        
        
        builder.append("\n----------------Fin ContainerBuilder----------------");
        
        return builder.toString();
    }
    
}
