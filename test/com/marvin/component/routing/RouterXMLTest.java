/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.routing;

import com.marvin.component.routing.config.Route;
import com.marvin.component.routing.xml.XMLRouteReader;

/**
 *
 * @author cdi305
 */
public class RouterXMLTest {
    
    public static void main(String[] args) {
        Router router = new Router();
        XMLRouteReader reader = new XMLRouteReader(router);
        reader.read("app/config/routing.xml");
        
        router.routes.forEach((String name, Route route) -> {
            System.out.println(name + " :: " + route.getPath() + " :: " + route.getController());
        });
        
    }
}
