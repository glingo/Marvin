/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.service.IService;
import com.marvin.old.kernel.Kernel;
//import com.marvin.bundle.framework.server.Server;
import com.marvin.bundle.framework.console.Console;
import com.marvin.old.dependency.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.app.AppKernel;

/**
 *
 * @author Dr.Who
 */
public class MyTest {

    public MyTest() {
        
        Kernel kernel = new AppKernel();
        
        Console console = new Console(kernel);
        console.start();

//        Server server = new Server(kernel, 46, 47);
//        server.start();
        
//        Application httpserver = new HTTPServer(kernel);
//        httpserver.start();
        
        try {
            kernel.boot();
            Container c = (Container) kernel.getContainer().get("container");
            IService service = (IService) c.get("test.service.c");
            service.sayHello();
            
//            Kernel a = (Kernel) c.get("kernel");
//            System.out.println(c);
//            System.out.println(a);
        } catch (Exception ex) {
            Logger.getLogger(MyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // instrumentation (profiling)
//        Instrumentation inst;
    }
}
