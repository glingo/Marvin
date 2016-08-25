package com.marvin.component.container.compiler;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.compiler.passes.CompilerPassInterface;

public class Compiler {
    
    protected PassConfig passConfig;

    public Compiler() {
        
    }
    
    public Compiler(PassConfig passConfig) {
        this.passConfig = passConfig;
    }

    public PassConfig getPassConfig() {
        
        if(this.passConfig == null) {
            this.passConfig = new PassConfig();
        }
        
        return this.passConfig;
    }
    
    public void compile(ContainerBuilder builder){
        getPassConfig().getPasses().forEach((CompilerPassInterface pass) -> {
            pass.accept(builder);
        });
    }
    
}
