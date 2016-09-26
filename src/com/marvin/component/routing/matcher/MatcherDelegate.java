package com.marvin.component.routing.matcher;

import com.marvin.component.routing.MatcherInterface;
import com.marvin.component.routing.RouteCollection;
import java.util.Collection;
import java.util.HashMap;

public class MatcherDelegate implements MatcherInterface {

    private final Collection<MatcherInterface> matchers;
    
    public MatcherDelegate(Collection<MatcherInterface> matchers) {
        this.matchers = matchers;
    }
    
    @Override
    public HashMap<String, Object> match(RouteCollection collection, String matchable) {
        
        if(this.matchers == null) {
            return null;
        }
        
        MatcherInterface matcher = this.matchers.stream().filter((MatcherInterface deleguate) -> {
            return deleguate.support(matchable);
        }).findFirst().orElse(null);
        
        if(matcher == null) {
            return null;
        }
        
        return matcher.match(collection, matchable);
    }

    @Override
    public boolean support(String matchable) {
        if(this.matchers == null) {
            return false;
        }
        
        return this.matchers.stream().anyMatch((MatcherInterface matcher) -> {
            return matcher.support(matchable);
        });
    }

    public Collection<MatcherInterface> getMatchers() {
        return matchers;
    }
    
}
