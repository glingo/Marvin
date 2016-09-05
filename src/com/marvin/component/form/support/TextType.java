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
public class TextType extends AbstractFormType<String> {

    public TextType(String name) {
        super(name);
    }

    @Override
    public void buildForm(FormBuilder builder) { }
    
}
