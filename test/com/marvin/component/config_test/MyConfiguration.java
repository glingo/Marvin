package com.marvin.component.config_test;

import com.marvin.component.config_test.builder.ParentNodeDefinitionInterface;
import com.marvin.component.config_test.builder.TreeBuilder;
import com.marvin.component.config_test.builder.definition.ArrayNodeDefinition;
import com.marvin.component.config_test.builder.definition.NodeDefinition;

/**
 *
 * @author cdi305
 */
public class MyConfiguration implements ConfigurationInterface {

    @Override
    public TreeBuilder getConfigTreeBuilder() throws Exception {
        TreeBuilder builder = new TreeBuilder();
        
        ParentNodeDefinitionInterface root = builder.root("montest");
        
        root                        // is a NodeDefinition
            .children()             // return the NodeBuilder
                .scalarNode("scalartest")
                    .info("Ceci est un scalar test")
                .end()
                .arrayNode("test")      // return a NodeDefinition
                .info("Ceci est un test")
                .children()
                  .scalarNode("scalartest").end()
                .end();
//                .children()         // return the NodeBuilder
//                .end()              // return the parent
//            .end();                 // return the parent
        
        return builder;
    }
    
}
