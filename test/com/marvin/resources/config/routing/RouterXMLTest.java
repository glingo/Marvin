package com.marvin.resources.config.routing;

import com.marvin.component.resource.ResourceService;
import com.marvin.component.resource.loader.ClasspathResourceLoader;
import com.marvin.component.resource.loader.FileResourceLoader;
import com.marvin.component.resource.reference.ResourceReference;
import com.marvin.component.routing.MatcherInterface;
import com.marvin.component.routing.Route;
import com.marvin.component.routing.Router;
import com.marvin.component.routing.matcher.PathMatcher;
import com.marvin.component.routing.xml.XmlRouteReader;
import java.io.File;

public class RouterXMLTest {
    
    public static void main(String[] args) {
        ResourceService resourceService = ResourceService.builder()
                .with(ResourceReference.FILE, FileResourceLoader.instance())
                .with(ResourceReference.CLASSPATH, new ClasspathResourceLoader(ClassLoader.getSystemClassLoader()))
                .build();
        XmlRouteReader reader = new XmlRouteReader(resourceService);
        MatcherInterface matcher = new PathMatcher();
        Router router = new Router(reader, "com/marvin/resources/config/routing.xml", matcher);
        
        System.out.println("\t Routes : ");
        router.getRouteCollection().getRoutes().forEach((String name, Route route) -> {
            System.out.println(route);
        });
        
//        Request request = Request.build("/hello/world2");
        System.out.println("\n\n\t matching /hello/world2 : ");
        System.out.println(router.match("/hello/world2"));
//        
//        
//        request = Request.build("/hello");
        System.out.println("\n\n\t matching /hello : ");
        System.out.println(router.match("/hello"));
//        
//        
//        request = Request.build("/test");
        System.out.println("\n\n\t matching /test : ");
        System.out.println(router.match("/test"));
        
    }
}
