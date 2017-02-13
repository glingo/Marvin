package com.marvin.bundle.framework.mvc.view.resolver;

import com.marvin.bundle.framework.mvc.view.IView;
import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.container.exception.ContainerException;

public class ContainerViewResolver extends ContainerAware implements IViewResolver {

    @Override
    public IView resolveView(String name) throws ContainerException {
        return getContainer().get(name, IView.class);
    }
}
