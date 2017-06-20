package com.marvin.bundle.framework.mvc.view;

import com.marvin.component.kernel.Kernel;
import com.marvin.component.kernel.bundle.Bundle;
import com.marvin.component.mvc.view.ViewInterface;
import com.marvin.component.mvc.view.ViewResolverInterface;
import com.marvin.component.resolver.PathResolver;
import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.StringUtils;
import java.lang.reflect.Constructor;

public class ViewClassResolver extends PathResolver<ViewInterface> implements ViewResolverInterface {

    private static final String ROOT_VIEW_PATTERN = "%s.resources.view.%sView";
    private static final String VIEW_PATTERN = "%s.resources.view.%s.%sView";
    private final Kernel kernel;

    public ViewClassResolver(Kernel kernel) {
        this.kernel = kernel;
    }

    public String parse(String viewName) throws Exception {
        String original = viewName;
        
        if (viewName.contains("::")) {
            String[] fragments = viewName.split("::");
            String view = fragments[1];
            return String.format(ROOT_VIEW_PATTERN, this.kernel.getRootDir(), StringUtils.capitalize(view));
        }
        
        String[] fragments = viewName.split(":");
        
        if(fragments.length != 3) {
            String msg = String.format("The '%s' view is not a valid 'a:b:c' view string.", viewName);
            throw new Exception(msg);
        }
        
        String name = fragments[0];
        String pkg = fragments[1];
        String view = fragments[2];
        
        Bundle bundle = this.kernel.getBundle(name);
        
        if(bundle == null) {
            String msg = String.format(
                "The '%s' (from the _view value '%s') does not exist or is not enabled in your kernel!.", 
                name,
                original
            );
            
            String alternative = StringUtils.findAlternative(name, this.kernel.getBundles().keySet());
            
            if(alternative != null) {
                msg += String.format("\nDid you mean '%s:%s:%s' ?\n", alternative, pkg, view);
            }
            
            throw new Exception(msg);
        }
        
        return String.format(VIEW_PATTERN, bundle.getNamespace(), pkg, StringUtils.capitalize(view));
    }
    
    @Override
    public ViewInterface resolve(String object) throws Exception {
        return doResolve(parse(object));
    }

    @Override
    public ViewInterface doResolve(String name) throws Exception {
        name = ClassUtils.convertResourcePathToClassName(name);
        Class clazz = ClassUtils.forName(name, this.getClass().getClassLoader());
        Constructor<ViewInterface> constructor = ClassUtils.getConstructorIfAvailable(clazz, new Class[]{String.class});
        ViewInterface result = constructor.newInstance(new Object[]{name});
        return result;
    }
}
