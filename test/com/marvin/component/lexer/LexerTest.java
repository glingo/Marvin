/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.lexer;

import com.marvin.component.io.IResource;
import com.marvin.component.io.loader.DefaultResourceLoader;
import com.marvin.component.io.loader.ResourceLoader;
import com.marvin.component.templating.lexer.Lexer;
import com.marvin.component.templating.lexer.Syntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi305
 */
public class LexerTest {
    
    public static void main(String[] args) {
        
        InputStream is = null;
        
        try {
            ResourceLoader loader = new DefaultResourceLoader();
            IResource resource = loader.load("com/marvin/config/view");
            
            Syntax syntax = new Syntax();
            Lexer lexer = new Lexer(syntax, null, null);
            is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            Reader reader = new BufferedReader(isr);
            
            lexer.tokenize(reader, "com/marvin/config/view");
            
            System.out.println(lexer.getTokens());
            
        } catch (Exception ex) {
            Logger.getLogger(LexerTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(LexerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
