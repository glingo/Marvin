package com.marvin.component.kernel.controller.argument;

import com.marvin.component.kernel.controller.ControllerReference;
import java.util.ArrayList;
import java.util.List;

public abstract class ArgumentResolver {
    
    private final ArgumentMetadataFactoryInterface factory;
    private final List<ArgumentValueResolverInterface> resolvers;

    public ArgumentResolver(ArgumentMetadataFactoryInterface factory, List<ArgumentValueResolverInterface> resolvers) {
        super();
        this.factory = factory;
        this.resolvers = resolvers;
    }
    
    public ArgumentResolver(List<ArgumentValueResolverInterface> resolvers) {
        this(new ArgumentMetadataFactory(), resolvers);
    }
    
    public List<Object> getArguments(Object request, Object response, ControllerReference controller){
        List<Object> arguments = new ArrayList<>();
        
        this.factory.createArgumentMetadata(controller).stream().forEach((ArgumentMetadata argument) -> {
            this.resolvers.stream().forEach((ArgumentValueResolverInterface resolver) -> {
                if(resolver.support(request, response, argument)) {
                    Object resolved = resolver.resolve(request, response, argument);
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

}
