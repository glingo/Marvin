package com.marvin.component.shell.parser;

import com.marvin.component.shell.MethodReference;

public abstract class Parser implements ParserInterface {

    @Override
    public abstract MethodReference parse(String line);
    
}
