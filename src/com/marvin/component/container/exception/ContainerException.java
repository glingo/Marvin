package com.marvin.component.container.exception;

public class ContainerException extends Exception {

    public ContainerException() {}

    public ContainerException(String message) {
        super(message);
    }

    public ContainerException(Throwable cause) {
        super(cause);
    }

    public ContainerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
