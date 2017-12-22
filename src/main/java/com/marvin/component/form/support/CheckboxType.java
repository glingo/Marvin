package com.marvin.component.form.support;

import com.marvin.component.form.AbstractFormType;
import com.marvin.component.form.FormBuilder;

public class CheckboxType extends AbstractFormType<String> {

    public CheckboxType() {
        super();
    }

    public CheckboxType(String name) {
        super(name);
    }

    @Override
    public void buildForm(FormBuilder builder) { }
    
}
