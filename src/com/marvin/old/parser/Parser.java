/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.old.parser;

/**
 *
 * @author Dr.Who
 * @param <P>
 * @param <R>
 */
public abstract class Parser<P, R> {
    
    public abstract R parse(P parsable) throws Exception;
}
