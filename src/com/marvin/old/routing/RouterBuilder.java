package com.marvin.old.routing;

import com.marvin.old.builder.Builder;

/**
 *
 * @author Dr.Who
 */
public class RouterBuilder extends Builder<Router, Route> {

    @Override
    public Router create() {
        if(this.product == null){
            this.product = new Router();
        }
        
        return this.product;
    }

    @Override
    public void prepareMaterial(String id, Route route) {
        this.product.addRoute(id, route);
    }

}
