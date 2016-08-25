package com.marvin.bundle.framework.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.marvin.component.kernel.Kernel;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

public class Server { 
    
    private final Kernel kernel;
    protected int port;
    protected int backlog;
    protected ServerSocket socket;

    public Server(Kernel kernel, int port, int blacklog) {
        this.kernel = kernel;
        this.port = port;
        this.backlog = blacklog;
    }

    public void start() {

        try {
            
            open();

            accept();
            
            terminate();

        } catch (Exception e) {
            if (!isStopped()) {
                System.out.println("Erreur lors de l'accepation de la connection.");
                System.err.println(e.getMessage());
            }
            e.printStackTrace();
            System.exit(-1);
        }
    }

    protected void accept() throws Exception {
//        dispatcher.dispatch(ServerEvents.ACCEPT, new ServerEvent(this));
                
        while (!isStopped()) {
            try (Socket remote = socket.accept()) {
                
                InputStream in = remote.getInputStream();
                OutputStream out = remote.getOutputStream();
                
                this.kernel.handle(in, out);
                
                remote.close();
            }
        }
    }
    
    private synchronized boolean isStopped() {
        return this.socket.isClosed();
    }

    public synchronized void terminate() throws Exception {

        if (this.socket == null || this.socket.isClosed()) {
            return;
        }
        
        this.socket.close();
    }

    private synchronized void open() {
        try {
            this.socket = new ServerSocket(this.port, this.backlog, InetAddress.getLocalHost());
        } catch (IOException e) {
            throw new RuntimeException("Impossible d'ouvrir le port : " + this.port, e);
        }

        System.out.println("Server listening ");
        System.out.println(this.socket.getInetAddress());
        System.out.println(this.socket.getLocalPort());
        System.out.println(this.socket.getLocalSocketAddress());
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getSocket() {
        return socket;
    }

    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

//    public Kernel getKernel() {
//        return kernel;
//    }
//
//    public void setKernel(Kernel kernel) {
//        this.kernel = kernel;
//    }

//    public Dispatcher<Event> getDispatcher() {
//        return dispatcher;
//    }
//
//    public void setDispatcher(Dispatcher<Event> dispatcher) {
//        this.dispatcher = dispatcher;
//    }
}
