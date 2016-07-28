package com.marvin.component.container;

import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Reference;
import com.marvin.old.util.classloader.ClassLoaderUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * The Container to build
     */
    protected Container container;

    public ContainerBuilder() {
        this.definitions = new ConcurrentHashMap<>();
        this.container = new Container();
    }

    public void addDefinition(String id, Definition definition) {
        this.definitions.put(id, definition);
    }

    public void build() {
        this.definitions.keySet().forEach(this::get);
    }
    
    public Object[] resolveArguments(Definition definition) {
        
        Object[] arguments = Arrays.stream(definition.getArguments()).map((Object arg) -> {

            if (arg instanceof Reference) {
                Reference ref = (Reference) arg;
                System.out.println("Resolution d'une reference vers : " + ref.getTarget());
                arg = get(ref.getTarget());
            }

            return arg;
        }).toArray();
        
        return arguments;
    }
    
    public Constructor<Object> resolveConstructor(Definition definition) {
        Class type = ClassLoaderUtil.loadClass(definition.getClassName());
        
        if(type == null) {
            return null;
        }
        
        int len = definition.getArguments().length;
        Constructor<Object>[] constructors = type.getConstructors();

        Predicate<Constructor<Object>> filter = (Constructor<Object> cstr) -> {
            return len == cstr.getParameterCount();
        };
        
        return Arrays.stream(constructors).filter(filter).findFirst().get();
    }
    
    public Object instanciate(String id, Definition definition){
        Object service = null;
        
        // resolution des arguments
        Object[] arguments = resolveArguments(definition);
        Constructor<Object> constructor = resolveConstructor(definition);
        
        // instatiation
        if (constructor != null) {
            try {
                System.out.println("instanciation du service : " + id);
                System.out.println("avec les arguments : " + Arrays.toString(arguments));
                service = constructor.newInstance(arguments);
            } catch (InstantiationException ex) {
                LOG.log(Level.WARNING, "InstantiationException, we could not instatiate the service", ex);
            } catch (IllegalAccessException ex) {
                LOG.log(Level.WARNING, "IllegalAccessException, we can not access to the constructor", ex);
            } catch (IllegalArgumentException ex) {
                LOG.log(Level.WARNING, "IllegalArgumentException, Oops something's wrong in arguments", ex);
            } catch (InvocationTargetException ex) {
                LOG.log(Level.WARNING, "InvocationTargetException, Oops something's wrong in constructor invocation", ex);
            }

        }
        
        System.out.println("resultat de l'instanciation : " + service);
        return service;
    }

    public Object get(String id) {
        System.out.println("Tentative de recuperation du service " + id);
        Object service;
        
        try {
            service = this.container.get(id);
        } catch (ContainerException ex) {
            // The service has not been instatiate yet, look into definition registry ?
            System.out.println("Le service n'est pas encore prêt, recherche dans les definitions");
            Definition def = this.definitions.get(id);
            service = instanciate(id, def);
            this.container.set(id, service);
        }

        return service;
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

}
