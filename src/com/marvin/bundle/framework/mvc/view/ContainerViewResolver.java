package com.marvin.bundle.framework.mvc.view;

import com.marvin.component.mvc.view.ViewInterface;
import com.marvin.component.mvc.view.ViewResolverInterface;
import com.marvin.component.resolver.ContainerResolver;

public class ContainerViewResolver extends ContainerResolver<ViewInterface> implements ViewResolverInterface {

    public ContainerViewResolver() {
        super(ViewInterface.class);
    }
}
