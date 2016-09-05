package com.marvin.bundle.framework.shell;

public interface ExecutionStrategy extends Terminable {
    
    Object execute(MethodReference reference);
    
    boolean isReady();
}
