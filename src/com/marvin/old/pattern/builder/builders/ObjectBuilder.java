package com.marvin.old.pattern.builder.builders;

import com.marvin.old.pattern.builder.Builder;

/**
 *
 * @author cdi305
 */
public class ObjectBuilder extends Builder<Object> {

    @Override
    public void build() {
        this.product = new Object();
    }
    
}
