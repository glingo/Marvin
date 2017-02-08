package com.marvin.bundle.framework;

import com.marvin.bundle.framework.container.compiler.RegisterSubscribersPass;
import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.compiler.PassConfig;
import com.marvin.component.kernel.bundle.Bundle;

public class FrameworkBundle extends Bundle {

    @Override
    public void build(ContainerBuilder builder) {
        super.build(builder);
        builder.addCompilerPass(new RegisterSubscribersPass(), PassConfig.BEFORE_REMOVING);
    }

    
    
}
