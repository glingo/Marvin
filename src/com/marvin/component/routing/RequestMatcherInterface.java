package com.marvin.component.routing;

import com.marvin.component.kernel.dialog.Request;
import java.util.HashMap;

public interface RequestMatcherInterface {
    
//    void match(RouteCollection collection, String path);
    
    boolean support(Request request);
    
    HashMap<String, Object> matchRequest(RouteCollection collection, Request request);
    
}
