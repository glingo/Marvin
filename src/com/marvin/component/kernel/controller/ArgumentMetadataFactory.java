package com.marvin.component.kernel.controller;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

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
