package com.marvin.bundle.framework.shell;

import com.marvin.component.util.ReflectionUtils;
import java.lang.reflect.Method;

public class SimpleExecutionStrategy implements ExecutionStrategy {
    
//    private final Class<?> mutex = SimpleExecutionStrategy.class;
    
    private Object invoke(Method method, Object instance, Object[] arguments){
        ReflectionUtils.makeAccessible(method);
        return ReflectionUtils.invokeMethod(method, instance, arguments);
    }

    @Override
    public Object execute(MethodReference reference) {
        
        // try synchronized with mutex
        
        return invoke(reference.getMethod(), reference.getInstance(), reference.getArguments());
    
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void terminate() {
        // do nothing
    }
    
    
}
