package com.marvin.component.kernel;

import com.marvin.component.container.Container;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.event.EventDispatcher;
import com.marvin.component.kernel.bundle.Bundle;
import com.marvin.component.kernel.event.KernelEvent;
import com.marvin.component.kernel.event.KernelEvents;

import java.net.URL;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Le Kernel va handle un inputstream et repondre sur un outputstream.
 * @author Dr.Who
 */
public abstract class Kernel {
    
    protected static final int thread = 10;
    protected boolean booted = false;
    protected Map<String, Bundle> bundles = new ConcurrentHashMap<>();
    
    /** c'est un service */
    protected EventDispatcher dispatcher = new EventDispatcher();
    /** c'est un service */
    protected Container container;

    public Kernel() {
        super();
    }

    abstract protected Bundle[] registerBundles();
    
    protected URL getConfigURL(){
        return this.getClass().getResource("config");
    }
    
    public void boot() {
        
        if(this.isBooted()) {
            return;
        }
        
        this.dispatcher.dispatch(KernelEvents.BEFORE_LOAD, new KernelEvent(this));
        
        this.initializeContainer();
        this.initializeBundles();
        
        this.booted = true;
        
        this.dispatcher.dispatch(KernelEvents.AFTER_LOAD, new KernelEvent(this));
    }
    
    protected void initializeBundles() {
        Collector<Bundle, ?, ConcurrentMap<String, Bundle>> c = Collectors.toConcurrentMap(Bundle::getName, Bundle::boot);
        this.bundles = Arrays.asList(this.registerBundles()).stream().collect(c);
    }
    
    protected void initializeContainer() {
        
        ContainerBuilder builder = new ContainerBuilder();
        XMLDefinitionReader reader = new XMLDefinitionReader(builder);
        reader.loadDefinitions("app/config/config.xml");
        
//        loader.load("parameters.xml");
//        loader.load("config.xml");
//        loader.load("routing.xml");
        
        builder.build();
        this.container = builder.getContainer();
        
        // Inject the kernel as a service
        this.container.set("kernel", this);
        // Inject the container as a service
        this.container.set("container", container);
        // Inject the kernel as a service
//        this.container.set("logger", this.logger);
        // Inject an event dispatcher
        this.container.set("event_dispatcher", this.dispatcher);
        // Inject a thread_pool
        this.container.set("thread_pool", Executors.newFixedThreadPool(thread));
    }
    
//    protected void initializeRouter(FileLocator locator){
//        
//        RouterBuilder builder = new RouterBuilder();
//        RouterLoader loader = new RouterLoader(locator, builder);
//        loader.load("routing.xml");
//        
//        this.router = builder.build();
//        // Inject the router as a service
//        container.set("router", this.router);
//    }
    
    public void terminate() {
//        this.dispatcher.dispatch(KernelEvents.TERMINATE, new KernelEvent(this));
//        this.pool.shutdown();
    }
    
    
    public boolean isBooted() {
        return this.booted;
    }
    
    public Container getContainer(){
        return this.container;
    }
    
}
