package com.marvin.bundle.framework.view;

import java.util.Locale;

public interface IViewresolver {
    
    IView resolveView(String name, Locale locale);
}
