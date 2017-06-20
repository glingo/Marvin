package com.marvin.bundle.framework.mvc.view;

import com.marvin.bundle.templating.TemplateView;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.kernel.Kernel;
import com.marvin.component.mvc.view.ViewInterface;
import com.marvin.component.mvc.view.ViewResolverInterface;
import com.marvin.component.resolver.BundleResourceResolver;
import com.marvin.component.templating.Engine;

public class TemplateViewResolver extends BundleResourceResolver<ViewInterface> implements ViewResolverInterface {

    private final Engine engine;

    public TemplateViewResolver(Kernel kernel, ResourceLoader loader, Engine engine, String prefix, String sufix) {
        super(kernel, loader, prefix, sufix);
        this.engine = engine;
    }

    @Override
    protected ViewInterface create(String name) {
        return new TemplateView(name, engine);
    }
}
