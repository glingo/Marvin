package com.marvin.component.container.extension;

import com.marvin.component.container.ContainerBuilder;
import java.util.HashMap;

public interface ExtensionInterface {

    public String getAlias();
    
    public void load(HashMap<String, Object> configs, ContainerBuilder builder);
}
