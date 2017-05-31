package com.marvin.component.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ResolverDelegate<I, O> implements ResolverInterface<I, O> {

    protected Collection<ResolverInterface<I, O>> resolvers;

    public ResolverDelegate() {
        this.resolvers = new ArrayList<>();
    }
    
    public ResolverDelegate(Collection<ResolverInterface<I, O>> resolvers) {
        this.resolvers = resolvers;
    }
    
    @Override
    public O resolve(I name) throws Exception {
        for (ResolverInterface<I, O> resolver : getResolvers()) {
            O view = resolver.resolve(name);
            if (Objects.nonNull(view)) {
                return view;
            }
        }
        
        return null;
    }

    public Collection<ResolverInterface<I, O>> getResolvers() {
        if(this.resolvers == null) {
            this.resolvers = new ArrayList<>();
        }
        return this.resolvers;
    }

    public void setResolvers(List<ResolverInterface<I, O>> resolvers) {
        this.resolvers = resolvers;
    }
    
    public void addResolver(ResolverInterface<I, O> resolver) {
        getResolvers().add(resolver);
    }
}
