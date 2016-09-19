/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.controller.argumentResolver;

import com.marvin.component.kernel.controller.ArgumentMetadata;
import com.marvin.component.kernel.controller.ArgumentValueResolverInterface;
import com.marvin.component.dialog.Request;

/**
 *
 * @author caill
 */
public class RequestAtributeValueResolver implements ArgumentValueResolverInterface  {

    @Override
    public boolean support(Request request, ArgumentMetadata argument) {
        System.out.println("support by name : " + argument.getName());
        System.out.println(request.getAttributes());
        return !argument.isIsVariadic() && request.getAttributes().containsKey(argument.getName());
    }

    @Override
    public Object resolve(Request request, ArgumentMetadata argument) {
        System.out.println("resolving by name : " + argument.getName());
        return request.getAttribute(argument.getName());
    }
    
}
