/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.routing;

import com.marvin.component.routing.config.Route;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class RouterProgrammaticTest {

    public static void main(String[] args) {

        Route route_A = new Route();
//        route_A.setName("test_route_a");
        route_A.setController("app.bundles.test.controller.DefaultController::charger");
        route_A.setPath("/");

        Route route_B = new Route();
//        route_B.setName("test_route_b");
        route_B.setController("app.bundles.test.controller.TestController::charger");
        route_B.setPath("/");

        Router router = new Router();

        router.addRoute("test_route_a", route_A);
        router.addRoute("test_route_b", route_B);
        
        Route result = router.find("/");
        
        System.out.println(result);

    }
}
