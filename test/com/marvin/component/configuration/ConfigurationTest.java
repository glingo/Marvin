/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration;

import com.marvin.component.configuration.builder.TreeBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class ConfigurationTest {
    
    
    public static void main(String[] args) {
        ConfigurationInterface conf = new MyConfiguration();
        
        TreeBuilder builder = conf.getConfigTreeBuilder();
        
        try {
            builder.buildTree();
        } catch (Exception ex) {
            Logger.getLogger(ConfigurationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
