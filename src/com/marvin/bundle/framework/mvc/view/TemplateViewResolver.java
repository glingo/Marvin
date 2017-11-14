package com.marvin.bundle.framework.mvc.view;

import com.marvin.bundle.templating.TemplateView;
import com.marvin.component.kernel.Kernel;
import com.marvin.component.mvc.view.ViewInterface;
import com.marvin.component.mvc.view.ViewResolverInterface;
import com.marvin.component.kernel.bundle.BundleResourceResolver;
import com.marvin.component.resource.ResourceService;
import com.marvin.component.templating.Engine;

public class TemplateViewResolver extends BundleResourceResolver<ViewInterface> implements ViewResolverInterface {

    private final Engine engine;

    public TemplateViewResolver(Kernel kernel, ResourceService resourceService, Engine engine, String prefix, String sufix) {
        super(kernel, resourceService, prefix, sufix);
        this.engine = engine;
    }

    @Override
    protected ViewInterface create(String name) {
        return new TemplateView(name, engine);
    }
}
