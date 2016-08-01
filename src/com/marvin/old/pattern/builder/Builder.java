/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.pattern.builder;

/**
 *
 * @author cdi305
 * @param <P>
 */
public abstract class Builder<P> implements IBuilder<P> {
    
    /* The product we build. */
    protected P product;

    @Override
    public P getProduct() {
        return this.product;
    }
}
