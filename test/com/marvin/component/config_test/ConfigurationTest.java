/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.config_test;

import com.marvin.component.config_test.builder.NodeInterface;
import com.marvin.component.config_test.builder.TreeBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class ConfigurationTest {
    
    
    public static void main(String[] args) {
        ConfigurationInterface conf = new MyConfiguration();
        try {
            TreeBuilder builder = conf.getConfigTreeBuilder();
        
            NodeInterface node = builder.buildTree();
            
            System.out.println(node);
        } catch (Exception ex) {
            Logger.getLogger(ConfigurationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
