package com.marvin.old.pattern.builder;

public abstract class Builder<P> implements IBuilder<P> {
    
    /* The product we build. */
    protected P product;

    @Override
    public P getProduct() {
        return this.product;
    }
}
