package com.marvin.component.mvc.controller.argument;

import com.marvin.component.mvc.controller.ControllerReference;
import java.util.ArrayList;
import java.util.List;

public class ArgumentResolver implements ArgumentResolverInterface {
    
    private final ArgumentMetadataFactoryInterface factory;
    private List<ArgumentValueResolverInterface> resolvers = new ArrayList<>();
    
    public ArgumentResolver() {
        this.factory = new ArgumentMetadataFactory();
    }
    
    public ArgumentResolver(ArgumentMetadataFactoryInterface factory) {
        super();
        this.factory = factory;
    }

    public ArgumentResolver(ArgumentMetadataFactoryInterface factory, List<ArgumentValueResolverInterface> resolvers) {
        this(factory);
        this.resolvers = resolvers;
    }
    
    public ArgumentResolver(List<ArgumentValueResolverInterface> resolvers) {
        this(new ArgumentMetadataFactory(), resolvers);
    }
    
    @Override
    public List<Object> getArguments(Object request, Object response, ControllerReference controller) {
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
    
    public void addResolver(ArgumentValueResolverInterface resolver) {
        getResolvers().add(resolver);
    }

    public ArgumentMetadataFactoryInterface getFactory() {
        return factory;
    }

    public void setResolvers(List<ArgumentValueResolverInterface> resolvers) {
        this.resolvers = resolvers;
    }

    public List<ArgumentValueResolverInterface> getResolvers() {
        return resolvers;
    }

}
