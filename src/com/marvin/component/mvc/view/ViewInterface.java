package com.marvin.component.mvc.view;

import com.marvin.bundle.framework.mvc.Handler;
import java.util.HashMap;

public interface ViewInterface<I, O> {
    
    void load() throws Exception;
    void render(Handler<I, O> handler, HashMap<String, Object> model, I request, O response) throws Exception;
}
