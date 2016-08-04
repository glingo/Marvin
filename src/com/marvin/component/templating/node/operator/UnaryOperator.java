/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.node.operator;

import com.marvin.component.templating.node.expression.UnaryExpression;

/**
 *
 * @author cdi305
 */
public class UnaryOperator extends Operator {
    
    protected int precedence;

    public UnaryOperator(String symbol, int precedence, Class<? extends UnaryExpression> nodeClass) {
        super(symbol, nodeClass);
        this.precedence = precedence;
    }
    
    public int getPrecedence(){
        return this.precedence;
    }
}
