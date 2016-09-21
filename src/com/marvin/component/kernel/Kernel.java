package com.marvin.component.kernel;

import com.marvin.component.container.Container;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.extension.ExtensionInterface;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.ClassPathResourceLoader;
import com.marvin.component.kernel.bundle.Bundle;
import com.marvin.component.dialog.controller.ControllerResolver;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Le Kernel va handle un inputstream et repondre sur un outputstream.
 *
 * @author Dr.Who
 */
public abstract class Kernel {
    
    protected static final String VERSION = "v0.1";
    
    protected static final int THREAD = 10;

    protected boolean booted = false;
    protected boolean debug = false;

    protected ControllerResolver resolver = new ControllerResolver();

    protected Map<String, Bundle> bundles;
    protected Container container;

    abstract protected Bundle[] registerBundles();

    public Kernel() { }

    public Kernel(boolean debug) {
        this.debug = debug;
    }
    
    public Map<String, Bundle> getBundles() {
        if(this.bundles == null) {
            this.initializeBundles();
        }
        return this.bundles;
    }
    
    public Bundle getBundle(String name){
        return getBundles().getOrDefault(name, null);
    }

    public void boot() {

        if (this.isBooted()) {
            return;
        }

        this.initializeBundles();
        
        this.initializeContainer();

        this.bundles.values().forEach((Bundle bundle) -> {
            bundle.setContainer(container);
            bundle.boot();
        });

        this.booted = true;

    }
    
    protected void initializeBundles() {
        this.bundles = Arrays.stream(registerBundles())
            .collect(Collectors.toConcurrentMap(Bundle::getName, (Bundle bundle) -> {return bundle;}));
    }
    
    protected void prepareContainer(ContainerBuilder builder) {
        this.bundles.values().forEach((Bundle bundle) -> {
            ExtensionInterface extension = bundle.getContainerExtension();
            builder.registerExtension(extension);
        });
    }

    protected void initializeContainer() {

        ClassPathResourceLoader loader = new ClassPathResourceLoader(this.getClass());

        ContainerBuilder builder = new ContainerBuilder();
        
        this.prepareContainer(builder);

        XMLDefinitionReader reader = new XMLDefinitionReader(loader, builder);

        reader.read("resources/parameters.xml");
        reader.read("resources/services.xml");

        // Inject the kernel as a service
        builder.set("kernel", this);
//         Inject the container as a service
        builder.set("container", builder.getContainer());
        builder.set("kernel.resource_loader", loader);
        // Inject a thread_pool
//        builder.set("thread_pool", Executors.newFixedThreadPool(THREAD));

        this.bundles.values().forEach((Bundle bundle) -> {
            bundle.build(builder);
        });

        this.container = builder.build();
    }

    public void terminate() {
//        this.dispatcher.dispatch(KernelEvents.TERMINATE, new KernelEvent(this));
//        this.pool.shutdown();
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
    
    
    /* Handles methods */
    
//    public Response handle(Request request) throws Exception {
//        
//        this.boot();
//        
//        return getRequestHandler().handle(request, true);
//        
//    }
    
    
//    public void handle(String row, Writer writer) throws Exception {
//
//        this.boot();
        
        // stack request
        
        // Dispatch event Request ( get response )
        
        // Find controller via resolver
        
        // filter controller via event
       
        // resolve arguments to pass
        
        // filter controller arguments via event

        // direct call controller
        
        // typer la response
        
        // filter la response ( pop request )
        
        
        // Toute cette partie est la recuperation de la Requette.
//        String uri = null;
//        Matcher matcher = Pattern.compile("^([A-Z]+) (\\p{Graph}+) ?((HTTP/[0-9\\.]+)?)$").matcher(row);
//
//        if (matcher.find()) {
////            String method = matcher.group(1);
//            uri = matcher.group(2);
////            String protocol = matcher.group(3);
//        }
//
//        if (uri == null && row.matches("^(\\p{Graph}+)$")) {
//            uri = row;
//        }
//
//        if (uri != null) {
//            StringWriter tmpWriter = new StringWriter();
//            this.container.set("print_writer", tmpWriter);
//            handle(Request.build(uri));
//            tmpWriter.flush();
//            writer.append(tmpWriter.toString());
//            tmpWriter.close();
//            this.container.set("print_writer", null);

//            Route route = this.router.find(uri);

//            if (route == null) {
//                String msg = String.format("Sorry we could find a route for %s\n", uri);
//                writer.append(msg);
//                return;
//            }
//
//            if (route.getController() == null) {
//                String msg = String.format("There is no Controller for %s\n", uri);
//                writer.append(msg);
//                return;
//            }

//            Controller controller = this.resolver.resolveController(route.getController());

//            if (controller == null) {
//                String msg = String.format("No controller set for %s\n", uri);
//                writer.append(msg);
//                return;
//            }
//
//            if (controller.getHolder() instanceof ContainerAwareInterface) {
//                ((ContainerAwareInterface) controller.getHolder()).setContainer(container);
//            }
//            
//            StringWriter tmpWriter = new StringWriter();
//            this.container.set("print_writer", tmpWriter);
//            controller.run();
//            tmpWriter.flush();
//            writer.append(tmpWriter.toString());
//            tmpWriter.close();
//            this.container.set("print_writer", null);
//
//            writer.flush();
//        }
//
//    }

//    public void handle(BufferedReader reader, Writer writer) throws Exception {
//
//        String line = reader.readLine();
//        
//        String request = "";
//        
//        while (line != null && !"".equals(line) && !System.lineSeparator().equals(line)) {
//            
//            request += "\n" + line;
//            
//            this.handle(line, writer);
//            
//            line = reader.readLine();
//        }
//        
//        System.out.println("Request :");
//        System.out.println(request);

//        while (line != null 
//                && !"".equals(line) 
//                && !System.lineSeparator().equals(line) 
//                && !line.equals("quit")) {
//            this.handle(line, writer);
//            line = reader.readLine();
//        }

//    }

//    public void handle(Reader reader, Writer writer) throws Exception {
//        BufferedReader buffered = new BufferedReader(reader);
//        this.handle(buffered, writer);
//    }
//
//    public void handle(InputStream in, OutputStream out) throws Exception {
//        PrintWriter writer = new PrintWriter(out, true);
//        Reader reader = new InputStreamReader(in);
//        this.handle(reader, writer);
//    }
//
//    public void handle(InputStream in, OutputStream out, OutputStream err) throws Exception {
//        this.handle(in, out);
//        out.flush();
//    }
