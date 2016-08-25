package com.marvin.component.shell;

import java.lang.reflect.Method;

public interface ExecutionStrategy extends Terminable {
    
    Object execute(Method method, Object instance, Object[] arguments);
    
    boolean isReady();
}
