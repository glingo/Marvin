/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.form.support;

import com.marvin.component.form.*;

public abstract class AbstractFormType<T> implements FormTypeInterface<T> {
    
    private String name;
    private String label;
    private T data;
    
    public AbstractFormType(String name) {
        this.name = name;
    }
    
    public AbstractFormType(String name, String label) {
        this.name = name;
        this.label = label;
    }
    
    @Override
    public abstract void buildForm(FormBuilder builder);
    
    @Override
    public FormBuilder createBuilder() {
        return new FormBuilder(getName());
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
