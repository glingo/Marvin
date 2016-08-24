package com.marvin.bundle.framework;

import com.marvin.bundle.framework.container.RegisterSubscribersPass;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.compiler.PassConfig;
import com.marvin.component.kernel.bundle.Bundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameworkBundle extends Bundle {

    @Override
    public void build(ContainerBuilder builder) {
        super.build(builder);
        
        try {
            builder.addCompilerPass(new RegisterSubscribersPass(), PassConfig.BEFORE_REMOVING);
        } catch (Exception ex) {
            Logger.getLogger(FrameworkBundle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
}
