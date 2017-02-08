package com.marvin.component.container.compiler.passes;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.extension.ExtensionInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MergeExtensionCompilerPass implements CompilerPassInterface {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    @Override
    public void accept(ContainerBuilder builder) {
        
        this.logger.info("Merging extensions.");
//        Map<String, Object> parameters = builder.getParameters();
//        Map<String, Definition> definitions = builder.getDefinitions();
//        Map<String, String> aliases = builder.getAliases();
        
        // expressionLanguage
        
        // deal with PrependExtension
        
        ContainerBuilder tmp = new ContainerBuilder();
        
        builder.getExtensions().forEach((String name, ExtensionInterface extension) -> {
            
            if(builder.getExtensionConfig(name) == null) {
                return;
            }
            
            Map<String, Object> config = builder.getExtensionConfig(name);
            
            this.logger.info(String.format("Merging extension %s %s.", name, config));
            extension.load(config, tmp);
        });
        
        builder.merge(tmp);
    }
    
}
