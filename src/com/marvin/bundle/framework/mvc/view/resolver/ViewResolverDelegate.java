package com.marvin.bundle.framework.mvc.view.resolver;

import com.marvin.bundle.framework.mvc.view.IView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ViewResolverDelegate extends ViewResolver {
    
    private Collection<IViewResolver> resolvers;

    public ViewResolverDelegate(Collection<IViewResolver> resolvers) {
        this.resolvers = resolvers;
    }
    
    @Override
    public IView resolveView(String name) throws Exception {
        
        for (IViewResolver resolver : getResolvers()) {
            IView view = resolver.resolveView(name);
            if (view != null) {
                return view;
            }
        }
        
        return null;
    }

    public Collection<IViewResolver> getResolvers() {
        if(this.resolvers == null) {
            this.resolvers = new ArrayList<>();
        }
        return this.resolvers;
    }

    public void setResolvers(List<IViewResolver> resolvers) {
        this.resolvers = resolvers;
    }
    
    public void addResolver(IViewResolver resolver) {
        getResolvers().add(resolver);
    }
    
}
