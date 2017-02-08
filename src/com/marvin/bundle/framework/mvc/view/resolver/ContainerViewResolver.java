package com.marvin.bundle.framework.mvc.view.resolver;

import com.marvin.bundle.framework.mvc.view.IView;
import com.marvin.component.container.IContainer;
import com.marvin.component.container.awareness.ContainerAware;
import com.marvin.component.container.exception.ContainerException;
import java.util.logging.Level;

public class ContainerViewResolver extends ContainerAware implements IViewResolver {

    @Override
    public IView resolveView(String name) throws ContainerException {
        IContainer container = getContainer();
        
        if(!container.contains(name)) {
            this.logger.log(Level.INFO, "No view has been found in container for name {0}", name);
            return null;
        }
        
        return container.get(name, IView.class);
    }
}
