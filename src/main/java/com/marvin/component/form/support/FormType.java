package com.marvin.component.form.support;

import com.marvin.component.form.AbstractFormType;
import com.marvin.component.form.FormBuilder;

public class FormType extends AbstractFormType<Object> {

    public FormType(String name, Object data) {
        super(name, null, data);
    }

    @Override
    public void buildForm(FormBuilder builder) {
    
    }
    
}
