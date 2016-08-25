package com.marvin.component.container.compiler.passes;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.extension.ExtensionInterface;
import java.util.HashMap;

public class MergeExtensionCompilerPass implements CompilerPassInterface {
    
    @Override
    public void accept(ContainerBuilder builder) {
//        Map<String, Object> parameters = builder.getParameters();
//        Map<String, Definition> definitions = builder.getDefinitions();
//        Map<String, String> aliases = builder.getAliases();
        
        // expressionLanguage
        
        // deal with PrependExtension
        
        ContainerBuilder tmp = new ContainerBuilder();
        
        builder.getExtensions().forEach((String name, ExtensionInterface extension) -> {
            HashMap<String, Object> config = builder.getExtensionConfig(name);
            
            if(builder.getExtensionConfig(name) == null) {
                return;
            }
            
            extension.load(config, tmp);
        });
        
        builder.merge(tmp);
    }
    
}
