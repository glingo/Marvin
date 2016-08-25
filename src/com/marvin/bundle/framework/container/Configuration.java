package com.marvin.bundle.framework.container;

import com.marvin.component.configuration.*;
import com.marvin.component.configuration.builder.TreeBuilder;
import com.marvin.component.configuration.builder.definition.NodeDefinition;

public class Configuration implements ConfigurationInterface {

    @Override
    public TreeBuilder getConfigTreeBuilder() throws Exception {
        TreeBuilder builder = new TreeBuilder();
        builder.root("framework");
        
//        NodeDefinition root = builder.root("framework");

//        root
//            .scalarNode("test").end()
//                .scalarNode("scalar test")
//                    .info("Ceci est un scalar test")
//                .end()
//
//                .arrayNode("1st array test")
//                    .scalarNode("scalartest")
//                        .info("Ceci est un scalar test")
//                        .defaultNull()
//                    .end()
//                    .booleanNode("boolean node")
//                        .info("Ceci est un boolean test")
//                    .end()
//                .end()
//
//                .scalarNode("2nd scalar test")
//                    .info("Ceci est un scalar test")
//                    .defaultFalse()
//                .end()
//
//                .arrayNode("2nd array test")
//                    .defaultValue(new HashMap<>())
//                    .scalarNode("scalartest")
//                        .info("Ceci est un scalar test")
//                    .end()
//                    .booleanNode("boolean node")
//                        .info("Ceci est un boolean test")
//                    .end()
//                .end()
//        ;
        
        return builder;
    }
    
}
