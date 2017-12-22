package com.marvin.component.form.support;

import com.marvin.component.form.AbstractFormType;
import com.marvin.component.form.FormBuilder;

public class TextType extends AbstractFormType<String> {

    public TextType() {
        super();
    }
    
    public TextType(String name) {
        super(name);
    }
    
    public TextType(String name, String data) {
        super(name, null, data);
    }
    
    public TextType(String name, String label, String data) {
        super(name, label, data);
    }

    @Override
    public void buildForm(FormBuilder builder) { }
    
}
