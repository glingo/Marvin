/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.parser;

import java.util.Arrays;

/**
 *
 * @author cdi305
 */
public class ParserDelegate implements Parser {

    Parser[] parsers;

    public ParserDelegate(Parser[] parsers) {
        this.parsers = parsers;
    }
    
    @Override
    public Object[] getTypeKeys() {
        return new Object[]{};
    }

    @Override
    public Object parse(Object parsed) {
        return new Object();
    }
    
}
