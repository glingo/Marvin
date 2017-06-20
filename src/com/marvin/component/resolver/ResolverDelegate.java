package com.marvin.component.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ResolverDelegate<I, O> implements Resolver<I, O> {

    protected Collection<Resolver> resolvers;

    public ResolverDelegate() {
        this.resolvers = new ArrayList<>();
    }
    
    public ResolverDelegate(Collection<Resolver> resolvers) {
        this.resolvers = resolvers;
    }
    
    @Override
    public O resolve(I name) throws Exception {
        for (Resolver<I, O> resolver : getResolvers()) {
            O result = resolver.resolve(name);
            if (Objects.nonNull(result)) {
                return result;
            }
        }
        
        return null;
    }

    public Collection<Resolver> getResolvers() {
        if(this.resolvers == null) {
            this.resolvers = new ArrayList<>();
        }
        return this.resolvers;
    }

    public void setResolvers(List<Resolver> resolvers) {
        this.resolvers = resolvers;
    }
    
    public void addResolver(Resolver resolver) {
        getResolvers().add(resolver);
    }
}
