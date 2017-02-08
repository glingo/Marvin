package com.marvin.bundle.framework.mvc;

import com.marvin.bundle.framework.mvc.model.Model;
import java.util.Map;

public class ModelAndView {
    
    // Typed as an object so we can store String and View objects in order to resolve it later.
    private Object view;
    private Model model;

    public ModelAndView() {
    }

    public ModelAndView(Object view, Model model) {
        this.view = view;
        this.model = model;
    }
    
    public ModelAndView(Object view) {
        this.view = view;
    }
    
    public void setView(Object view) {
        this.view = view;
    }
    
    public <T> T getView(Class<T> type) {
        return (T) view;
    }

    public Object getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setModel(Map<String, Object> model) {
        if(this.model == null) {
            this.model = new Model();
        }
        
        this.model.putAll(model);
    }
}
