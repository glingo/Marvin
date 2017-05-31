package com.marvin.component.container.compiler.passes;

import com.marvin.component.container.ContainerBuilder;
import com.marvin.component.container.extension.ExtensionInterface;
import java.util.Map;
import java.util.logging.Logger;

public class MergeExtensionCompilerPass implements CompilerPassInterface {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    @Override
    public void accept(ContainerBuilder builder) {
        Map<String, Object> parameters = builder.getParameters();
//        Map<String, Definition> definitions = builder.getDefinitions();
//        Map<String, String> aliases = builder.getAliases();
        
        // expressionLanguage
        
        // deal with PrependExtension
        
        ContainerBuilder tmp = new ContainerBuilder();
        
        tmp.addParameters(parameters);
        
        builder.getExtensions().forEach((String name, ExtensionInterface extension) -> {
            if(builder.getExtensionConfig(name) == null) {
                return;
            }
            
            Map<String, Object> config = builder.getExtensionConfig(name);
            extension.load(config, tmp);
        });
        
        builder.merge(tmp);
    }
    
}
