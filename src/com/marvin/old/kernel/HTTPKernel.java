package com.marvin.old.kernel;

public abstract class HTTPKernel extends Kernel {

    private int port;

    public HTTPKernel(int port) {
        super();
        this.port = port;
    }
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
