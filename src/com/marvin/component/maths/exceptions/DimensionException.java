package com.marvin.component.maths.exceptions;

public class DimensionException extends RuntimeException {

    /**
     * Constructs a dimension exception with no detail message.
     */
    public DimensionException() {
        super();
    }

    /**
     * Constructs a dimension exception with the specified message.
     * 
     * @param message the error message.
     */
    public DimensionException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}