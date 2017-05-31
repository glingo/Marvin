package com.marvin.component.mvc.view;

import com.marvin.bundle.framework.mvc.Handler;
import java.util.HashMap;
import java.util.logging.Logger;

public abstract class View<I, O> implements ViewInterface<I, O> {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    protected final String name;
    
    public View(String name) {
        this.name = name;
    }
    
    @Override
    public abstract void load() throws Exception;

    @Override
    public abstract void render(Handler<I, O> handler, HashMap<String, Object> model, I request, O response) throws Exception;
    
}
