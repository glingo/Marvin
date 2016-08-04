package com.marvin.component.container;

import com.marvin.component.container.awareness.ContainerAwareInterface;
import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.container.config.Definition;
import com.marvin.component.container.config.Parameter;
import com.marvin.component.container.config.Reference;
import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.ObjectUtils;
import com.marvin.component.util.ReflectionUtils;
import com.marvin.component.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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

    public Container build() {
        this.definitions.keySet().forEach(this::get);
        return this.container;
    }

    public Object resolveArgument(Object arg) {

        if (arg instanceof Reference) {
            Reference ref = (Reference) arg;
//                System.out.println("Resolution d'une reference vers : " + ref.getTarget());
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

    public Object[] resolveArguments(Object[] arguments) {
        return Arrays.stream(arguments).map(this::resolveArgument).toArray();
    }

    public Constructor<Object> resolveConstructor(Definition definition) {
        try {
            Class type = Class.forName(definition.getClassName());

            int len = definition.getArguments().length;
            Constructor<Object>[] constructors = type.getConstructors();

            Predicate<Constructor<Object>> filter = (Constructor<Object> cstr) -> {
                return len == cstr.getParameterCount();
            };

            return Arrays.stream(constructors).filter(filter).findFirst().orElse(type.getEnclosingConstructor());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContainerBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Object instanciate(String id, Definition definition) {
        Object service = null;

        if (StringUtils.hasLength(definition.getFactoryName())) {
            try {
                Class factory = ClassUtils.resolveClassName(definition.getFactoryName(), null);
                Method method = ClassUtils.getMethod(factory, definition.getFactoryMethodName(), new Class[]{});
                service = ReflectionUtils.invokeMethod(method, factory.newInstance());

            } catch (IllegalArgumentException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ContainerBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            // resolution des arguments
            Object[] arguments = resolveArguments(definition.getArguments());
            Constructor<Object> constructor = resolveConstructor(definition);

            // instatiation
            if (constructor != null) {
                try {
//                System.out.println("instanciation du service : " + id);
//                System.out.println("avec les arguments : " + Arrays.toString(arguments));
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
        }
        
        if (service != null) {
            if (service instanceof ContainerAwareInterface) {
                ((ContainerAwareInterface) service).setContainer(container);
            }

            this.container.set(id, service);
        }

//        System.out.println("resultat de l'instanciation : " + service);
        return service;
    }

    public Object get(String id) {
//        System.out.println("Tentative de recuperation du service " + id);
        Object service = null;

        try {
            service = this.container.get(id);
        } catch (ContainerException ex) {
            // The service has not been instatiate yet, look into definition registry ?
//            System.out.println("Le service n'est pas encore prêt, recherche dans les definitions");
            Definition def = this.definitions.get(id);
            if (def != null) {
                service = instanciate(id, def);
            }
        }

        return service;
    }

    public void set(String id, Object service) {
        this.container.set(id, service);
    }

    public void addParameter(String key, Object value) {
        this.container.setParameter(key, value);
    }

    public Object getParameter(String key) {
//        System.out.println("Tentative de recuperation du parametre " + key);
        return this.container.getParameter(key, null);
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
