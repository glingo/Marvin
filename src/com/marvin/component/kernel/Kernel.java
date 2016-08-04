package com.marvin.component.kernel;

import com.marvin.component.container.Container;
import com.marvin.component.container.awareness.ContainerAwareInterface;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.event.EventDispatcher;
import com.marvin.component.io.loader.ClassPathResourceLoader;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.kernel.bundle.Bundle;
import com.marvin.component.kernel.controller.Controller;
import com.marvin.component.kernel.controller.ControllerResolver;
import com.marvin.component.kernel.event.KernelEvent;
import com.marvin.component.kernel.event.KernelEvents;
import com.marvin.component.routing.Router;
import com.marvin.component.routing.config.Route;
import com.marvin.component.routing.xml.XMLRouteReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Le Kernel va handle un inputstream et repondre sur un outputstream.
 *
 * @author Dr.Who
 */
public abstract class Kernel {

    protected static final int THREAD = 10;
    
    protected boolean booted = false;
    protected boolean debug = false;

    protected ControllerResolver resolver = new ControllerResolver();
    protected EventDispatcher dispatcher = new EventDispatcher();
    
    protected Map<String, Bundle> bundles;
    protected Container container;
    protected Router router;

    abstract protected Bundle[] registerBundles();

    public Kernel() {
    }
    
    public Kernel(boolean debug) {
        this.debug = debug;
    }

    public void boot() {

        if (this.isBooted()) {
            return;
        }

        this.dispatcher.dispatch(KernelEvents.BEFORE_LOAD, new KernelEvent(this));
        
        ClassPathResourceLoader loader = new ClassPathResourceLoader(this.getClass());

        this.initializeBundles();
        this.initializeContainer(loader);
        this.initializeRouter(loader);
        
        this.bundles.values().forEach((Bundle bundle) -> {
            bundle.setContainer(container);
        });

        this.booted = true;

        this.dispatcher.dispatch(KernelEvents.AFTER_LOAD, new KernelEvent(this));

        if(debug) {
            
            System.out.println("\n\n\nKernel booted");

            System.out.println("----------------Liste des services----------------");
            this.getContainer().getServices().forEach((String id, Object service) -> {
                System.out.println(id + ": " + service);
            });

            System.out.println("-----------------Liste des routes-----------------");
            this.router.getRoutes().forEach((String name, Route route) -> {
                System.out.print("name : " + name);
                System.out.print(", controller : " + route.getController());
                System.out.print(", path : " + route.getPath());
                System.out.println();
            });
            
            System.out.println("\n\n\n");
        }
    }

    protected void initializeBundles() {
        this.bundles = Arrays.stream(registerBundles())
                .collect(Collectors.toConcurrentMap(Bundle::getName, Bundle::boot));
    }

    protected void initializeContainer(ResourceLoader loader) {

        ContainerBuilder builder = new ContainerBuilder();
        XMLDefinitionReader reader = new XMLDefinitionReader(builder, loader);
        
        reader.read("resources/parameters.xml");
        reader.read("resources/services.xml");
        
        this.bundles.values().forEach((Bundle bundle) -> {
            bundle.build(builder);
        });
        
        // Inject the kernel as a service
        builder.set("kernel", this);
        // Inject the container as a service
        builder.set("container", container);
        // Inject an event dispatcher
        builder.set("event_dispatcher", this.dispatcher);
        // Inject a thread_pool
        builder.set("thread_pool", Executors.newFixedThreadPool(THREAD));
        // Inject the logger as a service
//        this.container.set("logger", this.logger);
        
        this.container = builder.build();

    }

    protected void initializeRouter(ResourceLoader loader) {

        this.router = new Router();
        XMLRouteReader reader = new XMLRouteReader(router, loader);
        reader.read("resources/routing.xml");

        // Inject the router as a service
        container.set("router", this.router);
    }

    public void terminate() {
//        this.dispatcher.dispatch(KernelEvents.TERMINATE, new KernelEvent(this));
//        this.pool.shutdown();
    }

    public void handle(String row, PrintWriter writer) throws Exception {
        
        this.boot();
        
        // Toute cette partie est la recuperation de la Requette.
        String uri = null;
        Matcher matcher = Pattern.compile("^([A-Z]+) (\\p{Graph}+) ?((HTTP/[0-9\\.]+)?)$").matcher(row);

        if (matcher.find()) {
//            String method = matcher.group(1);
            uri = matcher.group(2);
//            String protocol = matcher.group(3);
        }

        if (uri == null && row.matches("^(\\p{Graph}+)$")) {
            uri = row;
        }

        if (uri != null) {

            Route route = this.router.find(uri);

            if (route == null) {
                writer.format("Sorry we could find a route for %s\n", uri);
                return;
            }
            
            if(route.getController() == null) {
                writer.format("There is no Controller for %s\n", uri);
                return;
            }

            
            Controller controller = this.resolver.createController(route.getController());

            if (controller == null) {
                writer.format("No controller set for %s\n", uri);
                return;
            }
            
            if(controller.getHolder() instanceof ContainerAwareInterface) {
                ((ContainerAwareInterface) controller.getHolder()).setContainer(container);
            }

            this.container.set("print_writer", writer);
            controller.run();
            this.container.set("print_writer", null);
        }

        writer.flush();

    }

    public void handle(BufferedReader reader, PrintWriter writer) throws Exception {

        String line = reader.readLine();
        

        while (line != null && !"".equals(line) && !System.lineSeparator().equals(line) && !line.equals("quit")) {
            this.handle(line, writer);
            line = reader.readLine();
        }

    }

    public void handle(Reader reader, PrintWriter writer) throws Exception {
        BufferedReader buffered = new BufferedReader(reader);
        this.handle(buffered, writer);
    }

    public void handle(InputStream in, OutputStream out) throws Exception {
        PrintWriter writer = new PrintWriter(out, true);
        Reader reader = new InputStreamReader(in);
        this.handle(reader, writer);
    }

    public void handle(InputStream in, OutputStream out, OutputStream err) throws Exception {
        this.handle(in, out);
        out.flush();
    }

    public boolean isBooted() {
        return this.booted;
    }

    public boolean isDebug() {
        return debug;
    }

    public Container getContainer() {
        return this.container;
    }

}
