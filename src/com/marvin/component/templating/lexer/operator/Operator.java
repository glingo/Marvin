/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.lexer.operator;

/**
 *
 * @author cdi305
 */
public abstract class Operator {
    
    protected String symbol;

    public Operator(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol(){
        return this.symbol;
    }
}
