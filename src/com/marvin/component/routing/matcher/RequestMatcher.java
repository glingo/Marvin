/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.routing.matcher;

import com.marvin.component.kernel.dialog.Request;
import com.marvin.component.routing.RequestMatcherInterface;
import com.marvin.component.routing.RouteCollection;
import com.marvin.component.routing.config.Route;
import java.util.HashMap;

public abstract class RequestMatcher implements RequestMatcherInterface {

    @Override
    public abstract boolean support(Request request);

    protected abstract HashMap<String, Object> matchCollection(String path, RouteCollection routes);
    
    public abstract HashMap<String, Object> match(RouteCollection collection, String path);
            
    @Override
    public abstract HashMap<String, Object> matchRequest(RouteCollection collection, Request request);

}
