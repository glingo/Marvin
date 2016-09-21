/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.routing;

import com.marvin.component.dialog.Request;
import com.marvin.component.routing.config.Route;
import com.marvin.component.routing.matcher.UriMatcher;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class RouterProgrammaticTest {

    public static void main(String[] args) {

        Route route_A = new Route();
        route_A.addDefault("_controller", "app.bundles.test.controller.DefaultController::charger");
        route_A.setPath("/");

        Route route_B = new Route();
        route_B.addDefault("_controller", "app.bundles.test.controller.TestController::charger");
        route_B.addDefault("name", "world");
        route_B.addRequirement("name", "\\w+");
        route_B.setPath("/hello/{name}");
        
        RouteCollection collection = new RouteCollection();

        collection.addRoute("test_route_a", route_A);
        collection.addRoute("test_route_b", route_B);

        RequestMatcherInterface matcher = new UriMatcher();
        Router router = new Router(collection, matcher);
        
        Request request = Request.build("/");
        HashMap<String, Object> result = router.matchRequest(request);
        System.out.println(result);
        
        Request request2 = Request.build("/hello/world2");
        HashMap<String, Object> world = router.matchRequest(request2);
        System.out.println(world);

    }
}
