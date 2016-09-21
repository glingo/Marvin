package com.marvin.component.routing.matcher;

import com.marvin.component.dialog.Request;
import com.marvin.component.routing.RouteCollection;
import java.util.HashMap;

public class UriMatcher extends RequestMatcher {

    @Override
    public boolean support(Request request) {
        return (request.getUri() != null);
    }

    @Override
    public HashMap<String, Object> matchRequest(RouteCollection collection, Request request) {
        return match(collection, request.getUri().getPath());
    }
}
