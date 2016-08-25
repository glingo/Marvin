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
            
            this.kernel.boot();

            this.kernel.handle(System.in, System.out, System.err);
            
            System.out.println("==============================================");
        } catch (Exception ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
