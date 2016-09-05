package com.marvin.bundle.framework.container.compiler;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.compiler.passes.CompilerPassInterface;
import com.marvin.component.container.config.Definition;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public class RegisterCommandsPass implements CompilerPassInterface {
    
    public final static String SHELL_NAME = "shell";
    public final static String COMMAND_TAG = "command";

    @Override
    public void accept(ContainerBuilder builder) {
        
        if(!builder.hasDefinition(SHELL_NAME)) {
            return;
        }
        
        Definition shell = builder.getDefinition(SHELL_NAME);
        
        HashMap<String, Definition> taggedDefinitions = builder.findTaggedDefinitions(COMMAND_TAG);
        
        taggedDefinitions.forEach((String name, Definition definition)->{
            
            // do nothing if not public
            
            // do nothing if abstract
            
            Object command = builder.get(name);
            
            shell.addCall("addCommand", command);
        });

    }
    
}
