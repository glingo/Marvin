/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.form.support;

import com.marvin.component.form.FormBuilder;

/**
 *
 * @author caill
 */
public class FormType extends AbstractFormType<String> {

    public FormType(String name) {
        super(name);
    }

    @Override
    public void buildForm(FormBuilder builder) { }
    
}
