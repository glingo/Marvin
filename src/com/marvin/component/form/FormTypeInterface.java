package com.marvin.component.form;

import java.util.List;

public interface FormTypeInterface<T> {
    
    int getIndex();
    void setIndex(int index);
    
    String getName();
    void setName(String name);
    
    String getLabel();
    void setLabel(String label);
    
    void setData(T data);
    T getData();
    
    void setParent(FormTypeInterface parent);
    FormTypeInterface getParent();
    void addChildren(List<FormTypeInterface> children);
    void addChild(FormTypeInterface child);
    
    List<FormTypeInterface> getChildren();
    
    void buildForm(FormBuilder builder);
    
    FormBuilder createBuilder();
}
