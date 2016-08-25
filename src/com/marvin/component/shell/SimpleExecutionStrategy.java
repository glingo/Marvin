package com.marvin.component.shell;

import com.marvin.component.util.ReflectionUtils;
import java.lang.reflect.Method;

public class SimpleExecutionStrategy implements ExecutionStrategy {
    
//    private final Class<?> mutex = SimpleExecutionStrategy.class;
    
    private Object invoke(Method method, Object instance, Object[] arguments){
        ReflectionUtils.makeAccessible(method);
        return ReflectionUtils.invokeMethod(method, instance, arguments);
    }

    @Override
    public Object execute(Method method, Object instance, Object[] arguments) {
        
        // try synchronized with mutex
        
        return invoke(method, instance, arguments);
    
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
