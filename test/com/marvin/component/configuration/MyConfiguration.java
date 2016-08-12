package com.marvin.component.configuration;
import com.marvin.component.configuration.builder.TreeBuilder;
import com.marvin.component.configuration.builder.definition.NodeDefinition;

/**
 *
 * @author cdi305
 */
public class MyConfiguration implements ConfigurationInterface {

    @Override
    public TreeBuilder getConfigTreeBuilder() {
        TreeBuilder builder = new TreeBuilder();
        try {
            
            NodeDefinition root = builder.root("montest");

            root

                .scalarNode("scalar test")
                    .info("Ceci est un scalar test")
                .end()

                .arrayNode("1st array test")
                    .scalarNode("scalartest")
                        .info("Ceci est un scalar test")
                    .end()
                    .booleanNode("boolean node")
                        .info("Ceci est un boolean test")
                    .end()
                .end()

                .scalarNode("2nd scalar test")
                    .info("Ceci est un scalar test")
                .end()

                .arrayNode("2nd array test")
                    .scalarNode("scalartest")
                        .info("Ceci est un scalar test")
                    .end()
                    .booleanNode("boolean node")
                        .info("Ceci est un boolean test")
                    .end()
                .end()
            ;
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return builder;
    }
    
}
