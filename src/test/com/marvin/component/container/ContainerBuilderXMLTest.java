/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.container;

import com.marvin.component.container.config.Definition;
import com.marvin.component.container.xml.XMLDefinitionReader;
import com.marvin.component.resource.ResourceService;
import com.marvin.component.resource.loader.ClasspathResourceLoader;
import com.marvin.component.resource.loader.FileResourceLoader;
import com.marvin.component.resource.reference.ResourceReference;
import java.util.Arrays;

/**
 *
 * @author cdi305
 */
public class ContainerBuilderXMLTest {

    public static void main(String[] args) {
        
        ResourceService resourceService = ResourceService.builder()
                .with(ResourceReference.FILE, FileResourceLoader.instance())
                .with(ResourceReference.CLASSPATH, new ClasspathResourceLoader(ClassLoader.getSystemClassLoader()))
                .build();
//        ResourceLoader loader = new FileSystemResourceLoader();
//        ResourceLoader loader = new ClassPathResourceLoader(ContainerBuilderXMLTest.class);
//        reader.loadDefinitions("C:\\Users\\cdi305\\Desktop\\GitHub\\Marvin\\demo\\app\\config\\config.xml");
//        reader.loadDefinitions("D:\\sources\\Marvin\\demo\\app\\config\\config.xml");

        ContainerBuilder builder = new ContainerBuilder();
        XMLDefinitionReader reader = new XMLDefinitionReader(resourceService, builder);
        reader.read("com/marvin/resources/config/services.xml");

        System.out.println("-------------------------------------------------");
        System.out.println("Liste des definitions");
        builder.getDefinitions().forEach((String id, Definition def) -> {
            System.out.println("id : " + id);
            System.out.println("instance : " + def);
            Arrays.stream(def.getArguments()).forEach((Object obj) -> {
                System.out.print("arg : ");
                System.out.print(obj);
                if(obj != null)
                    System.out.print("(" +obj.getClass() + ")");
            });
        });
        
        System.out.println("-------------------------------------------------");
        System.out.println("Construction du container");
        builder.build();

        System.out.println("-------------------------------------------------");
        System.out.println("Liste des services");
        builder.getContainer().getServices().forEach((String id, Object service) -> {
            System.out.println("id : " + id);
            System.out.println("instance : " + service);
        });

    }

}
