/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.bundle.framework.shell;

import com.marvin.component.kernel.Kernel;
import com.marvin.component.kernel.dialog.Request;
import com.marvin.component.kernel.dialog.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caill
 */
public class KernelShell {
    
    private final Kernel kernel;
    
    public KernelShell(Kernel kernel) {
        this.kernel = kernel;
    }
    public void handle(BufferedReader reader, Writer writer) throws Exception {

        String line = reader.readLine();
        
        while (line != null && !"quit".equals(line)) {
            Request request = Request.build(line);

            Response response = kernel.handle(request);
            
            if(response != null) {
                writer.append(response.getContent().toString());
            }
            
            
            writer.flush();

            line = reader.readLine();
        }

    }
    
    public void handle(Reader reader, Writer writer) throws Exception {
        BufferedReader buffered = new BufferedReader(reader);
        this.handle(buffered, writer);
    }

    public void handle(InputStream in, OutputStream out) throws Exception {
        PrintWriter writer = new PrintWriter(out, true);
        Reader reader = new InputStreamReader(in);
        this.handle(reader, writer);
    }
    
    public void start() {
        try {
            
            this.kernel.boot();
            
//            Console console = System.console();
//            
//            String line = console.readLine();
//
//            while (line != null && !"quit".equals(line)) {
//                Request request = Request.build(line);
//
//                kernel.handle(request);
//
//                line = console.readLine();
//            }
            
            handle(System.in, System.out);
            
            System.out.println("==============================================");
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
