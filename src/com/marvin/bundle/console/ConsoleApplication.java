package com.marvin.bundle.console;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.framework.Application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public abstract class ConsoleApplication extends Application {

    public ConsoleApplication(String env, boolean debug) {
        super(env, debug);
    }
    
    @Override
    public void ready() {
        super.ready();
        start();
    }
    
    private void start() {
        try {
            String quit = "quit";
            String home = "/";
            String referer = home;
            String cmd = home;
            
            while(!cmd.equalsIgnoreCase(quit)) {
                Command command = new Command(cmd);
                getHandler().handle(command, System.out, true);
                cmd = read();
            }
        } catch (Exception ex) {
            throw new Error("Something went wrong ! ", ex);
        }
    }
    
    private String read() throws IOException {
        if (System.console() != null) {
            return System.console().readLine();
        }
        Reader reader = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(reader);
        
        return bf.readLine();
    }
    
    private void print(String what) throws IOException {
        System.out.println(what);
    }
}
