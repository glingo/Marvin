package com.marvin.bundle.framework.mvc.view;

import java.util.HashMap;

public abstract class View<R, T> implements IView<R, T> {
    
    private String name;
    
    public View() {
    }

    public View(String name) {
        this.name = name;
    }
    
    @Override
    public abstract void render(HashMap<String, Object> model, R request, T response) throws Exception;
    
}
