/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.form;

/**
 *
 * @author caill
 */
public interface FormTypeInterface<T> {
    
    String getName();
    void setName(String name);
    
    void setData(T data);
    T getData();
    
    void buildForm(FormBuilder builder);
    
    FormBuilder createBuilder();
}
