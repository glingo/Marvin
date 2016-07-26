package test.app.bundles.test.controller;

import com.marvin.old.routing.mapping.Route;

public class DefaultController {

    @Route(name = "default.home", path = "/")//, reponse="ressources/view/nav.view.twig"
    public void charger() {
        System.out.println("default.charger");
    }
}
