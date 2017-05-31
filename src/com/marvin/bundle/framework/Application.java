package com.marvin.bundle.framework;

import com.marvin.component.container.IContainer;
import com.marvin.component.kernel.Kernel;
import com.marvin.bundle.framework.mvc.Handler;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public abstract class Application {
    
    protected static final Logger logger = Logger.getLogger(Application.class.getName());
    protected static Application application = null;
   
    private final Kernel kernel;
    private Handler handler;
    
    protected Application(Kernel kernel) {
        this.kernel = kernel;
    }
    
    protected Handler getHandler() {
        if(this.handler == null) {
            this.handler = getContainer().get("handler", Handler.class);
        }
        
        return this.handler;
    }
    
    public void initialize() throws Exception {
        this.handler = getHandler();
        startup();
        waitForReady();
        ready();
    }
    
    public void startup() throws Exception {
    }
    
    public void shutdown() {}
    
    public final void exit() {}
    
    public void end() {
        Runtime.getRuntime().exit(0);
    }
    
    public void waitForReady() {};
    public void ready() {};
    
    public IContainer getContainer(){
        return this.kernel.getContainer();
    }
    
    public static synchronized <T extends Application> T getInstance(Class<T> applicationClass, Kernel kernel) {
        if (application == null) {
            try {
                application = create(applicationClass, kernel);
            } catch (Exception e) {
                String msg = String.format("Couldn't construct %s", applicationClass);
                throw(new Error(msg, e));
            }
        }
	return applicationClass.cast(application);
    }
    
    public static synchronized <T extends Application> void launch(final Class<T> applicationClass, Kernel kernel) {
        try {
            application = create(applicationClass, kernel);
            application.initialize();
        } catch (Exception e) {
            String msg = String.format("Application %s failed to launch", applicationClass);
            throw(new Error(msg, e));
        }
    }
    
    public static <T extends Application> T create(Class<T> applicationClass, Kernel kernel) throws Exception {
        /* Construct the Application object.  The following
         * complications, relative to just calling
         * applicationClass.newInstance(), allow a privileged app to
         * have a private static inner Application subclass.
         */
        Constructor<T> ctor = applicationClass.getDeclaredConstructor(new Class[]{Kernel.class});
        if (!ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
        
        kernel.boot();
                
        T app = ctor.newInstance(kernel);
        return app;
    }
}
