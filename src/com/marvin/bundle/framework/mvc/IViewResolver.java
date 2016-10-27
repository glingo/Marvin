package com.marvin.bundle.framework.mvc;

import java.util.Locale;

public interface IViewResolver {
    
    IView resolveView(String name, Locale locale);
}
