package com.marvin.bundle.framework.shell.parser;

import com.marvin.bundle.framework.shell.MethodReference;

public abstract class Parser implements ParserInterface {

    @Override
    public abstract MethodReference parse(String line);
    
}
