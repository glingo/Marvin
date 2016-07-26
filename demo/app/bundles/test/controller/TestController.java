package test.app.bundles.test.controller;

import com.marvin.old.routing.mapping.Route;

public class TestController {

    @Route(name = "test.charger", path = "/test/charger")//, reponse="ressources/view/nav.view.twig"
    public void charger() {
        System.out.println("test.charger");
    }
}
