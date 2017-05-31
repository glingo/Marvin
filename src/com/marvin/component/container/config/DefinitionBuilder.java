package com.marvin.component.container.config;

import java.util.List;

public class DefinitionBuilder implements DefinitionBuilderInterface {

    
    public static void main(String[] args) {
        Definition def = DefinitionBuilderInterface.definition().name("test");
        System.out.println(def);
    }

    public Definition definition(String scope, String name) {
        return new Definition(scope, name);
    }

    @Override
    public Definition name(String name) {
        return new Definition(name);
    }
}
