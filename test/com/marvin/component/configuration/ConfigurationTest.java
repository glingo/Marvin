
package com.marvin.component.configuration;

import com.marvin.component.configuration.builder.NodeInterface;
import com.marvin.component.configuration.builder.TreeBuilder;
import com.marvin.component.configuration.builder.node.Node;
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
            
            
            
            System.out.println(builder);
            
//            builder.print();
        
            Node node = builder.buildTree();
            
            System.out.println(node);
        } catch (Exception ex) {
            Logger.getLogger(ConfigurationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
