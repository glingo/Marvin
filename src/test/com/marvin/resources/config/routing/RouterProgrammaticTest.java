package com.marvin.resources.config.routing;

import com.marvin.component.routing.MatcherInterface;
import com.marvin.component.routing.Route;
import com.marvin.component.routing.RouteCollection;
import com.marvin.component.routing.Router;
import com.marvin.component.routing.matcher.PathMatcher;
import java.util.HashMap;
import java.util.Map;

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

        MatcherInterface matcher = new PathMatcher();
        Router router = new Router(collection, matcher);
        
//        Request request = Request.build("/");
        Map<String, Object> result = router.match("/");
        System.out.println(result);
        
//        Request request2 = Request.build("/hello/world2");
        Map<String, Object> world = router.match("/hello/world2");
        System.out.println(world);

    }
}
