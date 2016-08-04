/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.node.operator;

import com.marvin.component.templating.node.expression.Expression;

/**
 *
 * @author cdi305
 */
public abstract class Operator {
    
    protected String symbol;
    
    protected Class<? extends Expression<?>> nodeClass;

    public Operator(String symbol, Class<? extends Expression<?>> nodeClass) {
        this.symbol = symbol;
        this.nodeClass = nodeClass;
    }
    
    public String getSymbol(){
        return this.symbol;
    }

    public Class<? extends Expression<?>> getNodeClass() {
        return nodeClass;
    }
    
}
