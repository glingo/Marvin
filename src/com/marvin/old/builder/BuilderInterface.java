package com.marvin.old.builder;

/**
 *
 * @author Dr.Who
 * @param <P>
 * @param <M>
 */
public interface BuilderInterface<P, M> {
    
    P create();
    P build();
    void stock(String k, M material);
    
}
