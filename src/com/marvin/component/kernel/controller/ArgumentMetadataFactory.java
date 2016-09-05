/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.controller;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caill
 */
public class ArgumentMetadataFactory implements ArgumentMetadataFactoryInterface {
    
    @Override
    public List<ArgumentMetadata> createArgumentMetadata(ControllerReference controller){
        
        List<ArgumentMetadata> arguments = new ArrayList<>();
        
        Parameter[] parameters = controller.getAction().getParameters();
        
        for (Parameter param : parameters) {
            arguments.add(new ArgumentMetadata(param.getName(), param.getType(), param.isVarArgs()));
        }
        
        return arguments;
    }
    
}
