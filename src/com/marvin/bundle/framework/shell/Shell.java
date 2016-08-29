package com.marvin.bundle.framework.shell;

import com.marvin.bundle.framework.shell.parser.ParserInterface;

public abstract class Shell implements ShellInterface {
    
    protected abstract String getHomeAsString();
    
    protected abstract ExecutionStrategy getExecutionStrategy();
    
    protected abstract ParserInterface getParser();
    
    @Override
    public void executeCommand(String line){
        
        // change status to parsing
        
        final ExecutionStrategy executionStrategy = getExecutionStrategy();
        final ParserInterface parser = getParser();
        
        MethodReference reference = parser.parse(line);
        
        Object result = executionStrategy.execute(reference);
        
    }
}
