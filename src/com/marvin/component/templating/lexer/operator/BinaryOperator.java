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
public class BinaryOperator extends Operator {
    
    protected int precedence;
    protected Associativity associativity;

    public BinaryOperator(String symbol, int precedence, Associativity associativity) {
        super(symbol);
        this.associativity = associativity;
        this.precedence = precedence;
    }
    
    public Associativity getAssociativity() {
        return associativity;
    }
    
    public int getPrecedence(){
        return this.precedence;
    }
}
