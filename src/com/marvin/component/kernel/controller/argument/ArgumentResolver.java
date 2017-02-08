package com.marvin.bundle.framework.controller.argument;

import com.marvin.bundle.framework.controller.ControllerReference;
import java.util.ArrayList;
import java.util.List;

public class ArgumentResolver {
    
    private final ArgumentMetadataFactoryInterface factory;
    private final List<ArgumentValueResolverInterface> resolvers;

    public ArgumentResolver(List<ArgumentValueResolverInterface> resolvers) {
        this.factory = new ArgumentMetadataFactory();
//        this.resolvers = getDefaultResolvers();
        this.resolvers = resolvers;
    }
    
    public ArgumentResolver(ArgumentMetadataFactoryInterface factory, List<ArgumentValueResolverInterface> resolvers) {
        this.factory = factory;
        this.resolvers = resolvers;
    }
    
    public List<Object> getArguments(Object request, ControllerReference controller){
        List<Object> arguments = new ArrayList<>();
        
        this.factory.createArgumentMetadata(controller).stream().forEach((ArgumentMetadata argument) -> {
            this.resolvers.stream().forEach((ArgumentValueResolverInterface resolver) -> {
                if(resolver.support(request, argument)) {
                    Object resolved = resolver.resolve(request, argument);
                    arguments.add(resolved);
                }
            });
        });
        
        return arguments;
    }
    
    
//    public static List<ArgumentValueResolverInterface> getDefaultResolvers(){
//        List<ArgumentValueResolverInterface> resolvers = new ArrayList<>();
//        
//        resolvers.add(new DefaultValueResolver());
//        resolvers.add(new RequestAtributeValueResolver());
//        resolvers.add(new RequestValueResolver());
//        
//        return resolvers;
//    } 
//    
    
}
