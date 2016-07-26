package com.marvin.old.dependency;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dr.Who
 */
public class Definition {
    
    protected String id;
    protected Class type;
    protected Object[] arguments;
    protected boolean deprecated = false;
    
    public Definition(String id) {
        this.id = id;
    }

    public Definition(String id, Class type) {
        this.id = id;
        this.type = type;
    }
    
    public Definition(String id, Class type, Object[] arguments) {
        this.id = id;
        this.type = type;
        this.arguments = arguments;
    }
        
    public Object[] getArguments() {
        return this.arguments;
    }
    
    public List<Object> getArgumentsAsList() {
        return Arrays.asList(this.arguments);
    }
    
    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
    
//    public void replaceArgument(int index, Object arg) {
//        Arrays.asList(this.arguments).set(index, arg);
//    }
//
//    public Object getArgument(int index) throws Exception
//    {
//        if (index < 0 || index > this.arguments.length) {
//            throw new Exception(String.format("The index '%d' is not in the range [0, %d].", index, this.arguments.length));
//        }
//
//        return Arrays.asList(this.arguments).get(index);
//    }
    
}
