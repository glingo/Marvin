package com.marvin.bundle.admin.container;

import com.marvin.component.configuration.ConfigurationInterface;
import com.marvin.component.configuration.builder.TreeBuilder;
import com.marvin.component.configuration.builder.definition.NodeDefinition;

public class Configuration implements ConfigurationInterface {

    @Override
    public TreeBuilder getConfigTreeBuilder() {
        TreeBuilder builder = new TreeBuilder();
        builder.root("console");
        
        NodeDefinition root = builder.root("admin");

//        root
//            .arrayNode("router")
//                .info("router configuration")
//                .scalarNode("resource")
//                    .info("Resource path")
//                    .required()
//                .end()
//            .end()
        
        return builder;
    }
    
}
