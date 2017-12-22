/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.parser;

/**
 *
 * @author caill
 */
public class ParserTest {
    
    public static void main(String[] args) {
        ParserResolver resolver = new ParserResolver();
        
        Parser parser = resolver.resolve(Boolean.class);
        
        System.out.println(parser.parse(Boolean.FALSE));
        System.out.println(parser.parse(false));
    }
}
