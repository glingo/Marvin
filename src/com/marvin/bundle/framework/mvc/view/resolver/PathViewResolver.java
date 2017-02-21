package com.marvin.bundle.framework.mvc.view.resolver;

import com.marvin.bundle.framework.mvc.view.IView;
import com.marvin.component.util.ClassUtils;
import java.io.File;
import java.lang.reflect.Constructor;

public class PathViewResolver extends ViewResolver {
    
    private String prefix;
    private String sufix;
    
    private Class clazz;

    public PathViewResolver(String prefix, String sufix, Class clazz) {
        this.prefix = prefix;
        this.sufix = sufix;
        this.clazz = clazz;
    }

    @Override
    public IView resolveView(String name) throws Exception {
        name = this.prefix + name + this.sufix;
        
        File file = new File(name);
        if(!file.exists()) {
            return null;
        }
        
        Constructor<IView> constructor = ClassUtils.getConstructorIfAvailable(this.clazz, new Class[]{String.class});
        return constructor.newInstance(new Object[]{name});
    }

    public String getSufix() {
        return sufix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSufix(String sufix) {
        this.sufix = sufix;
    }
    
}
