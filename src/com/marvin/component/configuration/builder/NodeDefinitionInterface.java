/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.configuration.builder;

/**
 *
 * @author cdi305
 */
public interface NodeDefinitionInterface {
    
    NodeBuilder children();
    
    NodeDefinitionInterface end();
    
}
