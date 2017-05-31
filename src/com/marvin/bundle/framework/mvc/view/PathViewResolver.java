package com.marvin.bundle.framework.mvc.view;

import com.marvin.component.mvc.view.ViewInterface;
import com.marvin.component.mvc.view.ViewResolverInterface;
import com.marvin.component.resolver.PathResolver;
import com.marvin.component.util.ClassUtils;
import com.marvin.component.util.StringUtils;
import java.lang.reflect.Constructor;
import java.util.logging.Level;

public class PathViewResolver extends PathResolver<ViewInterface> implements ViewResolverInterface {

    public PathViewResolver(String prefix, String sufix) {
        super(prefix, sufix);
    }

    @Override
    public ViewInterface resolve(String object) throws Exception {
        return doResolve(resolvePath(StringUtils.capitalize(object)));
    }

    @Override
    public ViewInterface doResolve(String name) throws Exception {
        ViewInterface result = null;

        try {
            name = ClassUtils.convertResourcePathToClassName(name);
            Class clazz = ClassUtils.forName(name, this.getClass().getClassLoader());
            Constructor<ViewInterface> constructor = ClassUtils.getConstructorIfAvailable(clazz, new Class[]{String.class});
            result = constructor.newInstance(new Object[]{name});
        } catch (Exception ex) {
            logger.getLogger(PathViewResolver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
