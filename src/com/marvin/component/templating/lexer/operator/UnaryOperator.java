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
public class UnaryOperator extends Operator {
    
    protected int precedence;

    public UnaryOperator(String symbol, int precedence) {
        super(symbol);
        this.precedence = precedence;
    }
    
    public int getPrecedence(){
        return this.precedence;
    }
}
