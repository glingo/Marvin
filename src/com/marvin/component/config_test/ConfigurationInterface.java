package com.marvin.component.config_test;

import com.marvin.component.config_test.builder.TreeBuilder;

public interface ConfigurationInterface {
    
    TreeBuilder getConfigTreeBuilder() throws Exception;
    
}
