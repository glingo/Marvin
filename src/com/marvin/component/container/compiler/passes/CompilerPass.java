package com.marvin.component.container.compiler.passes;

import com.marvin.component.container.ContainerBuilder;

@FunctionalInterface
public interface CompilerPass {

    public void accept(ContainerBuilder builder);
}
