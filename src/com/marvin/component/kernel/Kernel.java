package com.marvin.component.kernel;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Collector;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.IContainer;
import com.marvin.component.container.extension.ExtensionInterface;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.kernel.bundle.Bundle;
import java.util.HashMap;


public abstract class Kernel {
    
    protected static final String VERSION = "v0.1";
    
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    private final Collector<Bundle, ?, ConcurrentMap<String, Bundle>> bundleCollector = Collectors.toConcurrentMap(Bundle::getName, (Bundle bundle) -> {
        return bundle;
    });

    private String rootDir;
    private String environment;
    private boolean booted = false;
    private boolean debug  = false;
    private Map<String, Bundle> bundles;
    private IContainer container;
    
    abstract protected Bundle[] registerBundles();

    public Kernel() {
        this("dev", true);
    }

    public Kernel(String environment, boolean debug) {
        this.environment = environment;
        this.debug = debug;
    }
    
    public Map<String, Bundle> getBundles() {
        if(this.bundles == null) {
            initializeBundles();
        }
        return this.bundles;
    }
    
    public Bundle getBundle(String name){
        return getBundles().getOrDefault(name, null);
    }

    public void boot() {
        
        this.logger.info("Booting kernel ...");

        if (isBooted()) {
            this.logger.info("Kernel is already booted ...");
            return;
        }

        initializeBundles();
        
        initializeContainer();

        this.logger.info("Booting bundles ...");
        this.bundles.values().forEach((Bundle bundle) -> {
            this.logger.info(String.format("Booting bundle %s.", bundle.getName()));
            bundle.setContainer(container);
            bundle.boot();
        });

        this.booted = true;

    }
    
    protected Map<String, Object> getKernelParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("kernel.root_dir", getRootDir());
        
        return parameters;
    }
    
    protected void initializeBundles() {
        this.logger.info("Initializing bundles ...");
        this.bundles = Arrays.stream(registerBundles()).collect(this.bundleCollector);
    }
    
    protected void prepareContainer(ContainerBuilder builder) {
        this.logger.info("Preparing container ...");
        
        // add kernel parameters.
        builder.addParameters(getKernelParameters());
        
        this.bundles.values().forEach((Bundle bundle) -> {
            this.logger.info(String.format("Registering %s bundle extension ...", bundle.getName()));
            ExtensionInterface extension = bundle.getContainerExtension();
            builder.registerExtension(extension);
        });
    }
    
    protected void registerContainerConfiguration(ContainerBuilder builder) {
        this.logger.info("Registering Container configuration :");
        this.logger.info(String.format("%s/config/config_%s.xml", getRootDir(), getEnvironment()));
        
        
        XMLDefinitionReader reader  = new XMLDefinitionReader(builder);
        reader.read(String.format("%s/config/config_%s.xml", getRootDir(), getEnvironment()));
    }

    protected void initializeContainer() {
        this.logger.info("Initializing container ...");
        
        ContainerBuilder builder = new ContainerBuilder();
        prepareContainer(builder);
        
        registerContainerConfiguration(builder);

        this.bundles.values().forEach((Bundle bundle) -> {
            bundle.build(builder);
        });
        
        // Inject the kernel as a service.
        this.logger.info("Inject kernel as a service in container");
        builder.set("kernel", this);
        
        // Inject the resource loader.
//        this.logger.info("Inject resource loader as a service in container");
//        builder.set("kernel.resource_loader", loader);
        
        // Inject the container as a service.
        this.logger.info("Inject container it self as a service in container");
        builder.set("container", builder.getContainer());

        this.container = builder.build();

        // Inject a thread_pool
//        builder.set("thread_pool", Executors.newFixedThreadPool(THREAD));

    }

    public void terminate() {
        this.logger.info("Terminating kernel ...");
    }

    /* GETTERS and SETTERS */
    
    public boolean isBooted() {
        return this.booted;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public IContainer getContainer() {
        return this.container;
    }
    
    public String getEnvironment(){
        return this.environment;
    }
    
    protected String getRootDir() {
        if(this.rootDir == null) {
//            Matcher.quoteReplacement(File.separator)
            this.rootDir = getClass().getPackage().getName().replaceAll("\\.", "/");
        }
        return this.rootDir;
    }
    
}