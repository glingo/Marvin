package com.marvin.bundle.javafx;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.kernel.bundle.Bundle;

public class JavafxBundle extends Bundle {

    @Override
    public void build(ContainerBuilder builder) {
        super.build(builder);
        
//        try {
//            builder.addCompilerPass(new RegisterSubscribersPass(), PassConfig.BEFORE_REMOVING);
//        } catch (Exception ex) {
//            Logger.getLogger(FrameworkBundle.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    
    
}
