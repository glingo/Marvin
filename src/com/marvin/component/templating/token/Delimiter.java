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
public enum Delimiter {
    
    COMMENT("{#", "#}"),
    EXECUTE("{%", "%}"),
    PRINT("{{", "}}");
    
    private final String open;
    private final String close;
    
    private Delimiter(String open, String close) {
        this.open = open;
        this.close = close;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }
}
