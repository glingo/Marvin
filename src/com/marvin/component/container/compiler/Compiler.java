package com.marvin.component.container.compiler;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.compiler.passes.CompilerPassInterface;
import java.util.logging.Logger;

public class Compiler {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
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
        this.logger.info("Compiling contianer.");
        getPassConfig().getPasses().forEach((CompilerPassInterface pass) -> {
            this.logger.info(String.format("Applying pass %s.", pass.getClass().getName()));
            pass.accept(builder);
        });
    }
    
}
