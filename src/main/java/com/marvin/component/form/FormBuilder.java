package com.marvin.component.form;

import com.marvin.component.form.support.CheckboxType;
import com.marvin.component.form.support.FormType;
import com.marvin.component.form.support.PasswordType;
import com.marvin.component.form.support.TextType;
import java.util.HashMap;

public class FormBuilder {
    
    private FormTypeInterface type;
    private Object data;
    
    private final String name;
    private HashMap<String, FormBuilder> children;
//    private HashMap<String, FormTypeInterface> types;
    
    public FormBuilder(String name, Object data) {
        this.name = name;
        this.data = data;
    }
    
    public FormBuilder(String name, FormTypeInterface type) {
        this.name = name;
        this.type = type;
    }
    
    public FormTypeInterface getForm(){
        
        if(this.type == null) {
            this.type = new FormType(name, this.data);
        }
        
        getChildren().values().forEach((FormBuilder builder) -> {
            this.type.addChild(builder.getForm());
        });
        
        return this.type;
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
//        type.setName(name);
//        getTypes().put(name, type);
        add(name, create(name, type));
        return this;
    }
    
    public FormBuilder add(String name, Class clazz) throws Exception {
        add(name, create(name, (FormTypeInterface) clazz.newInstance()));
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
//            .append("\n---")
//            .append(super.toString())
            .append("name : ")
            .append(this.name) 
            .append(", children : ")
            .append(getChildren());
        
        return sb.toString();
    }
    
    
    private static class LoginForm {
        private String login;
        private String password;

        public LoginForm() {
        }

        public LoginForm(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        FormBuilder builder = new FormBuilder("testForm", new LoginForm());
        
        FormTypeInterface form = builder
                .add("username", new TextType("User :"))
                .add("password", new PasswordType("Password :"))
                .add("remember_me", new CheckboxType("Remember me :"))
                .add("username", new TextType("User initilized:", "user"))
                .add("usernameClass", TextType.class)
                .add("usernameClass", TextType.class)
                .getForm();
        
//        System.out.println(builder);
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
