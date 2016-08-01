package com.marvin.component.kernel;

import com.marvin.component.container.Container;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.event.EventDispatcher;
import com.marvin.component.io.loader.ClassPathResourceLoader;
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

    protected ControllerResolver resolver = new ControllerResolver();
    protected EventDispatcher dispatcher = new EventDispatcher();
    
    protected Map<String, Bundle> bundles;
    protected Container container;
    protected Router router;

    abstract protected Bundle[] registerBundles();

    public void boot() {

        if (this.isBooted()) {
            return;
        }

        this.dispatcher.dispatch(KernelEvents.BEFORE_LOAD, new KernelEvent(this));

        this.initializeContainer();
        this.initializeBundles();
        this.initializeRouter();

        this.booted = true;

        this.dispatcher.dispatch(KernelEvents.AFTER_LOAD, new KernelEvent(this));

        System.out.println("Kernel booted");

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
    }

    protected void initializeBundles() {
        this.bundles = Arrays.stream(registerBundles())
                .collect(Collectors.toConcurrentMap(Bundle::getName, Bundle::boot));
    }

    protected void initializeContainer() {

        ContainerBuilder builder = new ContainerBuilder();
        ClassPathResourceLoader loader = new ClassPathResourceLoader(this.getClass());
        XMLDefinitionReader reader = new XMLDefinitionReader(builder, loader);
        
//        reader.read("app/config/parameters.xml");
//        reader.read("app/config/config.xml");
        reader.read("config/parameters.xml");
        reader.read("config/config.xml");
        
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
        this.container.set("thread_pool", Executors.newFixedThreadPool(THREAD));
    }

    protected void initializeRouter() {

//        Router router = new Router();
        this.router  = new Router();
        XMLRouteReader reader = new XMLRouteReader(router);
        reader.read("app/config/routing.xml");

//        RouterLoader loader = new RouterLoader(locator, builder);
//        loader.load("routing.xml");
//        this.router = builder.build();
        // Inject the router as a service
        container.set("router", this.router);
    }

    public void terminate() {
//        this.dispatcher.dispatch(KernelEvents.TERMINATE, new KernelEvent(this));
//        this.pool.shutdown();
    }

    public void handle(String row, PrintWriter writer) throws Exception {
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
            this.boot();

            Route route = this.router.find(row);

            if (route == null || route.getController() == null) {
                writer.format("Sorry we could find a route for %s\n", row);
                return;
            }

            Controller controller = this.resolver.createController(route.getController());

            if (controller == null) {
                writer.format("No controller set for %s\n", row);
                return;
            }

            controller.run();
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

    public Container getContainer() {
        return this.container;
    }

}
