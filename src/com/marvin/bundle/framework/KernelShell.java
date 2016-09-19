package com.marvin.bundle.framework;

import com.marvin.component.kernel.Kernel;
import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;
import com.marvin.component.dialog.Response;
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
    private RequestHandler handler;
    
    public KernelShell(Kernel kernel) {
        this.kernel = kernel;
    }
    public void handle(BufferedReader reader, Writer writer) throws Exception {

        String line = reader.readLine();
        
        while (line != null && !"quit".equals(line)) {
            Request request = Request.build(line);
            
            Response response = getRequestHandler().handle(request, true);

//            Response response = kernel.handle(request);
            
            if(response != null) {
                writer.append(response.getContent().toString());
            }
            
            
            writer.flush();

            line = reader.readLine();
        }

    }
    
    public RequestHandler getRequestHandler(){
        if(this.handler == null) {
            this.handler = this.kernel.getContainer()
                    .get("request_handler", RequestHandler.class);
        }
        return this.handler;
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
