package com.marvin.component.form.support;

import com.marvin.component.form.AbstractFormType;
import com.marvin.component.form.FormBuilder;

public class PasswordType extends AbstractFormType<String> {

    public PasswordType() {
        super();
    }

    public PasswordType(String name) {
        super(name);
    }

    @Override
    public void buildForm(FormBuilder builder) { }
    
}
