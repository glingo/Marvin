package com.marvin.component.shell;

public abstract class Shell implements ShellInterface {
    
    protected abstract String getHomeAsString();
    
    protected abstract ExecutionStrategy getExecutionStrategy();
    
    
    @Override
    public void executeCommand(String line){
        
        // change status to parsing
        
        final ExecutionStrategy executionStrategy = getExecutionStrategy();
        
//        Object result = executionStrategy.execute(null, line, arguments);
        
    }
}
