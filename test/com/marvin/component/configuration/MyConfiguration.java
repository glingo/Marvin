/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration;

import com.marvin.component.configuration.builder.TreeBuilder;
import com.marvin.component.configuration.definition.NodeDefinition;

/**
 *
 * @author cdi305
 */
public class MyConfiguration implements ConfigurationInterface {

    @Override
    public TreeBuilder getConfigTreeBuilder() {
        TreeBuilder builder = new TreeBuilder();
        
        NodeDefinition root = builder.root("montest");
        
//        root.children().arrayNode("test").end();
        
        return builder;
    }
    
}
