/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.token;

/**
 *
 * @author cdi305
 */
public enum Type {
    EOF, 
    TEXT, 
    EXECUTE_START, 
    EXECUTE_END, 
    PRINT_START, 
    PRINT_END, 
    NAME, 
    NUMBER, 
    STRING, 
    OPERATOR, 
    PUNCTUATION
}
