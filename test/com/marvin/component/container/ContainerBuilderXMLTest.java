/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container;

import com.marvin.component.container.xml.DocumentLoader;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.io.loader.FileSystemResourceLoader;
import com.marvin.component.io.loader.ResourceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class ContainerBuilderXMLTest {
    
    public static void main(String[] args) {
        ContainerBuilder builder = new ContainerBuilder();
        ResourceLoader loader = new FileSystemResourceLoader();
        XMLDefinitionReader reader = new XMLDefinitionReader(builder, loader);
        
        reader.loadDefinitions("C:\\Users\\cdi305\\Desktop\\GitHub\\Marvin\\demo\\app\\config\\config.xml");
        //reader.loadDefinitions("C:\\Users\\cdi305\\Desktop\\GitHub\\Marvin\\demo\\app\\config\\config.xml");
    }
    
}
