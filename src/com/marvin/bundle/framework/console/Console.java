package com.marvin.bundle.framework.console;

//import com.marvin.old.kernel.Kernel;
import com.marvin.component.kernel.Kernel;
//import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.marvin.console.listener.ConsoleSubscriber;

public class Console {
    
    private final Kernel kernel;
    
    public Console(Kernel kernel) {
        this.kernel = kernel;
    }
    
    public void start() {
        try {
            
            System.out.println("==============================================");
            System.out.println("==                MARVIN                    ==");
            System.out.println("==              Console v0.1                ==");
            System.out.println("==============================================");
            
            this.kernel.handle(System.in, System.out, System.err);
            
            System.out.println("==============================================");
        } catch (Exception ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//        try {
////            this.dispatcher.dispatch(ConsoleEvents.START, new ConsoleEvent(this));
////            System.console().
////            this.kernel.handle(System.console().reader(), System.console().writer());
//            this.kernel.handle(System.in, System.out, System.err);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    
//    public Dispatcher<Event> getDispatcher() {
//        return dispatcher;
//    }
//
//    public void setDispatcher(Dispatcher<Event> dispatcher) {
//        this.dispatcher = dispatcher;
//    }
}
