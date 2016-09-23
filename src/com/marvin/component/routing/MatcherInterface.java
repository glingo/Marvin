package com.marvin.component.routing;

import java.util.HashMap;

public interface MatcherInterface {

    boolean support(String matchable);
    
    HashMap<String, Object> match(RouteCollection collection, String matchable);
    
}
