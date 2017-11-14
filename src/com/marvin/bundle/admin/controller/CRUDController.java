package com.marvin.bundle.admin.controller;

import com.marvin.bundle.admin.business.CRUDManager;
import com.marvin.component.form.FormBuilder;
import com.marvin.component.form.FormTypeInterface;
import com.marvin.component.form.support.ButtonType;
import com.marvin.component.form.support.FormType;
import com.marvin.component.form.support.PasswordType;
import com.marvin.component.form.support.TextType;
import com.marvin.component.mvc.ModelAndView;
import com.marvin.component.mvc.model.Model;
import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.Optional;

public class CRUDController {
    
    private CRUDManager manager;

    public CRUDController(CRUDManager manager) {
        this.manager = manager;
    }
    
    public ModelAndView list() {
        return ModelAndView.builder()
                .model("values", manager.findAll())
                .view("Admin:crud:list")
                .build();
    }
    
    public ModelAndView show(Object value) {
        return ModelAndView.builder()
                .model("value", value)
                .view("Admin:crud:show")
                .build();
    }
    
    public ModelAndView show(Long id) {
        return show(this.manager.findById(id));
    }
    
    public ModelAndView form(Model model) {
        Optional<FormTypeInterface> form = model.get("form", FormTypeInterface.class);
        
        if (!form.isPresent()) {
            Object value = manager.create();
            FormBuilder builder = createFormBuilder("value", value);
            
            ReflectionUtils.doWithFields(manager.getType(), (Field field) -> {
                Class c = field.getType();
                
                if (ClassUtils.isAssignable(c, String.class)) {
                    builder.add(field.getName(), new TextType(field.getName()));
                }
                
                builder.add("save", new ButtonType("save", "Save", "/admin/crud/create"));
                builder.add("return", new ButtonType("cancel", "cancel", "/admin/crud/list"));
            });
            
            return ModelAndView.builder()
                    .model(model)
                    .model("form", builder.getForm())
                    .view("Admin:crud:form")
                    .build();
        }
        
        Object value = form.get().getData();
        return show(value);
    }
    
    public ModelAndView create() {
//        this.manager.save(location);
        return ModelAndView.builder()
                .view("Admin:crud:create")
                .build();
    }
    
    protected FormBuilder createFormBuilder(String name, Object data) {
        FormBuilder builder = new FormBuilder(name, new FormType(name, data));
        return builder;
    }
}
