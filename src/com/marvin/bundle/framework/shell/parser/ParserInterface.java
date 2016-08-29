package com.marvin.bundle.framework.shell.parser;

import com.marvin.bundle.framework.shell.MethodReference;

public interface ParserInterface {
    
    MethodReference parse(String line);
    
}
