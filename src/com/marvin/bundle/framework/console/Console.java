package com.marvin.bundle.framework.console;

import com.marvin.component.kernel.Kernel;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.println("==           debug mode : " + kernel.isDebug() +"              ==");
            System.out.println("==============================================");
            
            this.kernel.handle(System.in, System.out, System.err);
            
            System.out.println("==============================================");
        } catch (Exception ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
