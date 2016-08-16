package com.marvin.bundle.framework.container;
import com.marvin.component.configuration.*;
import com.marvin.component.configuration.builder.TreeBuilder;
import com.marvin.component.configuration.builder.definition.NodeDefinition;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public class Configuration implements ConfigurationInterface {

    @Override
    public TreeBuilder getConfigTreeBuilder() {
        TreeBuilder builder = new TreeBuilder();
        try {
            
            NodeDefinition root = builder.root("framework");

            root

                .scalarNode("scalar test")
                    .info("Ceci est un scalar test")
                .end()

                .arrayNode("1st array test")
                    .scalarNode("scalartest")
                        .info("Ceci est un scalar test")
                        .defaultNull()
                    .end()
                    .booleanNode("boolean node")
                        .info("Ceci est un boolean test")
                    .end()
                .end()

                .scalarNode("2nd scalar test")
                    .info("Ceci est un scalar test")
                    .defaultFalse()
                .end()

                .arrayNode("2nd array test")
                    .defaultValue(new HashMap<>())
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
