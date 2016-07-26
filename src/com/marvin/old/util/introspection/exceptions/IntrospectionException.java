package com.marvin.old.util.introspection.exceptions;

public class IntrospectionException extends RuntimeException {

	/** Serial. */
	private static final long serialVersionUID = 1301881290087301393L;
	
	private String target;
	
	private String operation;
	
	public IntrospectionException(String message) {
		super(message);
	}
	
	public IntrospectionException(String message, String target, String operation) {
		super(message);
		setTarget(target);
		setOperation(operation);
	}
	
	public IntrospectionException(String message, String target, String operation, Throwable throwing) {
		super(message, throwing);
		setTarget(target);
		setOperation(operation);
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
