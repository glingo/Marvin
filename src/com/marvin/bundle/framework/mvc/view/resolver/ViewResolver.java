package com.marvin.bundle.framework.mvc.view.resolver;

import com.marvin.bundle.framework.mvc.view.IView;

public abstract class ViewResolver implements IViewResolver {
    
    @Override
    public abstract IView resolveView(String name) throws Exception;
    
}
