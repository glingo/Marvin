package com.marvin.component.resolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class PathResolver<O> extends Resolver<String, O> {

    private String prefix;
    private String sufix;
    
    public PathResolver() {
    }
    
    public PathResolver(String prefix, String sufix) {
        this.prefix = prefix;
        this.sufix = sufix;
    }
    
    @Override
    public O resolve(String object) throws Exception {
        return doResolve(resolvePath(object));
    };
    
    protected abstract O doResolve(String object) throws Exception;
    
    protected String resolvePath(String path) throws IOException {
        return Arrays.asList(this.prefix, path, this.sufix).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining());
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
