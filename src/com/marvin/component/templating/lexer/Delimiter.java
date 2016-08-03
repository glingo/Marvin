/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.lexer;

/**
 *
 * @author cdi305
 */
public enum Delimiter {
    
    COMMENT_OPEN("{#"),
    COMMENT_CLOSE("#}"),
    
    EXECUTE_OPEN("{%"),
    EXECUTE_CLOSE("%}"),
    
    PRINT_OPEN("{{"),
    PRINT_CLOSE("}}"),
    
    WS_TRIM("-");
    
    private final String symbol;
    
    private Delimiter(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
