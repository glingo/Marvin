/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container;

import com.marvin.component.container.xml.DocumentLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class ContainerBuilderXMLTest {
    
    public static void main(String[] args) {
        DocumentLoader loader = new DocumentLoader();
        
        
        try {
            loader.load(null, DocumentLoader.VALIDATION_AUTO, true);
        } catch (Exception ex) {
            Logger.getLogger(ContainerBuilderXMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
