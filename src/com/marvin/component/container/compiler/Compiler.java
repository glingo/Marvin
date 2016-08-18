package com.marvin.component.container.compiler;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.compiler.passes.CompilerPassInterface;

public class Compiler {
    
    protected PassConfig passConfig;

    public Compiler() {
        this.passConfig = new PassConfig();
    }

    public PassConfig getPassConfig() {
        return passConfig;
    }
    
    public void compile(ContainerBuilder builder){
        this.getPassConfig().getPasses().forEach((CompilerPassInterface pass) -> {
            pass.accept(builder);
        });
    }
    
}
