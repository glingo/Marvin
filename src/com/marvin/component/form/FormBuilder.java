package com.marvin.component.form;

import com.marvin.component.form.support.CheckboxType;
import com.marvin.component.form.support.PasswordType;
import com.marvin.component.form.support.TextType;
import java.util.HashMap;

public class FormBuilder {
    
    private final String name;
    private HashMap<String, FormBuilder> children;
//    private HashMap<String, FormTypeInterface> types;
    
    public FormBuilder(String name) {
        this.name = name;
    }
    
    public Form getForm(){
        Form form = new Form(this.name);
        
        getChildren().values().forEach((FormBuilder builder) -> {
            form.addChild(builder.getForm());
        });
        
        return form;
    }
    
//    public HashMap<String, FormTypeInterface> getTypes() {
//        if(this.types == null) {
//            this.types = new HashMap<>();
//        }
//        
//        return this.types;
//    }

    public HashMap<String, FormBuilder> getChildren() {
        if(this.children == null) {
            this.children = new HashMap<>();
        }
        
        return this.children;
    }

    public FormBuilder children(HashMap<String, FormBuilder> children) {
        getChildren().putAll(children);
        return this;
    }
    
    public FormBuilder add(String name, FormTypeInterface type) {
        type.setName(name);
//        getTypes().put(name, type);
        add(name, create(name, type));
        return this;
    }
    
    private FormBuilder add(String name, FormBuilder child) {
        getChildren().put(name, child);
        return this;
    }
    
    private FormBuilder get(String name) {
        return getChildren().get(name);
    }
    
    private FormBuilder create(String name, FormTypeInterface type) {
       type.setName(name);
       FormBuilder builder = type.createBuilder();
       type.buildForm(this);
       return builder;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("\n---")
            .append(super.toString())
            .append("---")
            .append("\n\tname : \n\t\t")
            .append(this.name) 
            .append("\n\tchildren : \n\t\t")
            .append(getChildren())
            .append("\n---- * ----\n");
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        
        FormBuilder builder = new FormBuilder("testForm");
        
        Form form = builder
                .add("username", new TextType("User :"))
                .add("password", new PasswordType("Password :"))
                .add("remember_me", new CheckboxType("Remember me :"))
                .getForm();
        
        System.out.println(builder);
        System.out.println(form);
        
    }
    
//    public FormBuilder create(String name) {
//        
//        this.form = new Form(name);
//        
//        return this;
//    }
//    
//    
//    public FormBuilder field(String label, FormType field) {
//        
//        if(this.form == null) {
//            return this;
//        }
//        
//        this.form.addField(label, field);
//        
//        return this;
//    }
    
//    public static void main(String[] args) {
//        FormBuilder builder = new FormBuilder();
//        
//        Form stringForm = builder
//                .create("string form")
//                .field("string :", new TextType())
//                .getForm();
//        
//        Form form = builder
//                .create("test form")
//                .field("first", new TextType())
//                .child(stringForm)
//                .getForm();
//        
//        System.out.println(form);
//        
//    }
    
}
