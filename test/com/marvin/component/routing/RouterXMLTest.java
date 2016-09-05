/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.routing;

import com.marvin.component.routing.config.Route;
import com.marvin.component.routing.matcher.UriMatcher;
import com.marvin.component.routing.xml.XmlRouteReader;

/**
 *
 * @author cdi305
 */
public class RouterXMLTest {
    
    public static void main(String[] args) {
        XmlRouteReader reader = new XmlRouteReader();
        RequestMatcherInterface matcher = new UriMatcher();
        Router router = new Router(reader, "com/marvin/resources/config/routing.xml", matcher);
        
        router.getRouteCollection().getRoutes().forEach((String name, Route route) -> {
            System.out.println(name + " :: " + route.getPath() + " :: " + route.getDefaults());
        });
        
    }
}
