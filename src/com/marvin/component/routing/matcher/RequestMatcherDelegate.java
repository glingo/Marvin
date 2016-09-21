package com.marvin.component.routing.matcher;

import com.marvin.component.dialog.Request;
import com.marvin.component.routing.RequestMatcherInterface;
import com.marvin.component.routing.RouteCollection;
import java.util.Collection;
import java.util.HashMap;

public class RequestMatcherDelegate implements RequestMatcherInterface {

    private final Collection<RequestMatcherDelegate> matchers;
    
    public RequestMatcherDelegate(Collection<RequestMatcherDelegate> matchers) {
        this.matchers = matchers;
    }
    
    @Override
    public HashMap<String, Object> matchRequest(RouteCollection collection, Request request) {
        
        if(this.matchers == null) {
            return null;
        }
        
        RequestMatcherInterface matcher = this.matchers.stream().filter((RequestMatcherInterface deleguate) -> {
            return deleguate.support(request);
        }).findFirst().orElse(null);
        
        if(matcher == null) {
            return null;
        }
        
        return matcher.matchRequest(collection, request);
    }

    @Override
    public boolean support(Request request) {
        if(this.matchers == null) {
            return false;
        }
        
        return this.matchers.stream().anyMatch((RequestMatcherInterface matcher) -> {
            return matcher.support(request);
        });
    }

    public Collection<RequestMatcherDelegate> getMatchers() {
        return matchers;
    }
    
    
}
