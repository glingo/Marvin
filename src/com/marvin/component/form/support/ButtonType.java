package com.marvin.component.form.support;

import com.marvin.component.form.AbstractFormType;
import com.marvin.component.form.FormBuilder;

public class ButtonType extends AbstractFormType<String> {
    
    private String action;
    
    public ButtonType() {
        super();
    }

    public ButtonType(String name, String label, String action) {
        super(name, label, null);
        this.action = action;
    }

    @Override
    public void buildForm(FormBuilder builder) { }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
