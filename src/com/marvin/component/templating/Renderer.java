/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating;

import java.io.PrintWriter;
import java.util.HashMap;

/**
 *
 * @author cdi305
 */
public class Renderer {
    
    protected PrintWriter writer;

    public Renderer(PrintWriter writer) {
        this.writer = writer;
    }
    
    public void render(Template template, HashMap<String, Object> parameters){
        
    }
    
}
