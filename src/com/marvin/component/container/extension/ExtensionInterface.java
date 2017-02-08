package com.marvin.component.container.extension;

import com.marvin.component.container.ContainerBuilder;
import java.util.Map;

public interface ExtensionInterface {

    public String getAlias();
    
    public void load(Map<String, Object> configs, ContainerBuilder builder);
}
