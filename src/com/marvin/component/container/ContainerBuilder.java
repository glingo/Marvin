package com.marvin.component.container;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.marvin.component.container.config.Definition;
import com.marvin.component.container.extension.ExtensionInterface;
import com.marvin.component.container.compiler.Compiler;
import com.marvin.component.container.compiler.PassConfig;
import com.marvin.component.container.compiler.passes.CompilerPassInterface;

public class ContainerBuilder {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    protected Compiler compiler;
    
    /** The Container to build. */
    protected Container container;

    protected Map<String, ExtensionInterface> extensions;

    protected Map<String, Map<String, Object>> extensionConfigs;

    public ContainerBuilder() {
    }

    public Container build() {
        this.logger.info("Building a Container.");
        
        compile();
        
        this.logger.info("Container has been built.");
        
        return getContainer();
    }
    
    private void compile(){
        this.logger.info("Compiling a Container.");
        getCompiler().compile(this);
        this.logger.info("Container is compiled.");
    }
    
    /* Container methods */
    
    public Container getContainer() {
        if(this.container == null) {
            this.container = new Container();
        }
        
        return this.container;
    }

    public Object get(String id) {
        return getContainer().get(id);
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
        return getContainer().getDefinitions();
    }

    public void setDefinitions(ConcurrentMap<String, Definition> definitions) {
        getContainer().setDefinitions(definitions);
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
        if(null == pass) {
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

}
