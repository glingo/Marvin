package com.marvin.component.routing;

import com.marvin.component.dialog.Request;
import java.util.HashMap;

public interface RequestMatcherInterface {

    boolean support(Request request);
    
    HashMap<String, Object> matchRequest(RouteCollection collection, Request request);
    
}
