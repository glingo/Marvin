package com.marvin.bundle.framework.mvc.view;

import java.util.HashMap;

public abstract class View<R, T> implements IView<R, T> {

    @Override
    public abstract void render(HashMap<String, ?> model, R request, T response) throws Exception;
    
}
