/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container.extension;

import com.marvin.component.container.ContainerBuilder;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public interface ExtensionInterface {

    public String getAlias();
    
    public void load(HashMap<String, Object> configs, ContainerBuilder builder);
}
