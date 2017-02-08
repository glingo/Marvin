package com.marvin.bundle.framework.mvc.view;

import java.util.HashMap;

public interface IView<R, T> {
    
    void render(HashMap<String, ?> model, R request, T response) throws Exception;
}
