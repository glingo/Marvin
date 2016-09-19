package com.marvin.component.shell;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class MethodReference {
    
    protected final Method method;
    protected final Object instance;
    protected final Object[] arguments;

    public MethodReference(Method method, Object instance, Object[] arguments) {
        this.method = method;
        this.instance = instance;
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.method);
        hash = 97 * hash + Objects.hashCode(this.instance);
        hash = 97 * hash + Arrays.deepHashCode(this.arguments);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MethodReference other = (MethodReference) obj;
        if (!Objects.equals(this.method, other.method)) {
            return false;
        }
        if (!Objects.equals(this.instance, other.instance)) {
            return false;
        }
        return Arrays.deepEquals(this.arguments, other.arguments);
    }

    
    
    
}
