package com.marvin.bundle.framework.mvc.view.resolver;

import com.marvin.bundle.framework.mvc.view.IView;

public interface IViewResolver {
    
    IView resolveView(String name) throws Exception;
}
