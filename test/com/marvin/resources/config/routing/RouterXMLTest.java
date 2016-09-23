package com.marvin.resources.config.routing;

import com.marvin.component.routing.MatcherInterface;
import com.marvin.component.routing.Route;
import com.marvin.component.routing.Router;
import com.marvin.component.routing.matcher.PathMatcher;
import com.marvin.component.routing.xml.XmlRouteReader;

public class RouterXMLTest {
    
    public static void main(String[] args) {
        XmlRouteReader reader = new XmlRouteReader();
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
