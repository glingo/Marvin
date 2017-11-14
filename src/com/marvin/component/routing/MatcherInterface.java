package com.marvin.component.routing;

import java.util.Map;

public interface MatcherInterface {

    boolean support(String matchable);
    
    Map<String, Object> match(RouteCollection collection, String matchable);
}
