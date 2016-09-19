package com.marvin.component.shell.parser;

import com.marvin.component.shell.MethodReference;

public interface ParserInterface {
    
    MethodReference parse(String line);
    
}
