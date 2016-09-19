package com.marvin.component.kernel.controller;

import com.marvin.component.kernel.controller.argumentResolver.DefaultValueResolver;
import com.marvin.component.kernel.controller.argumentResolver.RequestAtributeValueResolver;
import com.marvin.component.kernel.controller.argumentResolver.RequestValueResolver;
import com.marvin.component.dialog.Request;
import java.util.ArrayList;
import java.util.List;

public class ArgumentResolver {
    
    private ArgumentMetadataFactoryInterface factory;
    private List<ArgumentValueResolverInterface> resolvers;

    public ArgumentResolver() {
        this.factory = new ArgumentMetadataFactory();
        this.resolvers = getDefaultResolvers();
    }
    
    public ArgumentResolver(ArgumentMetadataFactoryInterface factory, List<ArgumentValueResolverInterface> resolvers) {
        this.factory = factory;
        this.resolvers = resolvers;
    }
    
    public List<Object> getArguments(Request request, ControllerReference controller){
        List<Object> arguments = new ArrayList<>();
        
        this.factory.createArgumentMetadata(controller).stream().forEach((ArgumentMetadata argument) -> {
            this.resolvers.stream().forEach((ArgumentValueResolverInterface resolver) -> {
                if(resolver.support(request, argument)) {
                    Object resolved = resolver.resolve(request, argument);
                    arguments.add(resolved);
                }
            });
        });
        
        System.out.println(this.getClass() + " ::getArguments");
        System.out.println(arguments);
        
        return arguments;
    }
    
    
    public static List<ArgumentValueResolverInterface> getDefaultResolvers(){
        List<ArgumentValueResolverInterface> resolvers = new ArrayList<>();
        
        resolvers.add(new DefaultValueResolver());
        resolvers.add(new RequestAtributeValueResolver());
        resolvers.add(new RequestValueResolver());
        
        return resolvers;
    } 
    
    
}
