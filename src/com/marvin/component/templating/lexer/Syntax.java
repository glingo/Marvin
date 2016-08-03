/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.lexer;

import java.util.regex.Pattern;

/**
 *
 * @author cdi305
 */
public class Syntax {
    
    private static final String POSSIBLE_NEW_LINE = "(\r\n|\n\r|\r|\n|\u0085|\u2028|\u2029)?";
    
    private final Pattern regexPrintClose;

    private final Pattern regexExecuteClose;

    private final Pattern regexCommentClose;

    private final Pattern regexStartDelimiters;

    private final Pattern regexLeadingWhitespaceTrim;

    private final Pattern regexTrailingWhitespaceTrim;

    public Syntax(final String commentOpen, final String commentClose, final String executeOpen, final String executeClose, final String printOpen, final String printClose, final String wsTrim) {
        
        this.regexPrintClose = Pattern.compile("^\\s*" + Pattern.quote(wsTrim) + "?" + Pattern.quote(printClose) + POSSIBLE_NEW_LINE);
        this.regexExecuteClose = Pattern.compile("^\\s*" + Pattern.quote(wsTrim) + "?" + Pattern.quote(executeClose) + POSSIBLE_NEW_LINE);
        this.regexCommentClose = Pattern.compile(Pattern.quote(commentClose) + POSSIBLE_NEW_LINE);
        
        this.regexStartDelimiters = Pattern.compile(Pattern.quote(printOpen) + "|" + Pattern.quote(executeOpen) + "|" + Pattern.quote(commentOpen));
        
        this.regexLeadingWhitespaceTrim = Pattern.compile(Pattern.quote(wsTrim) + "\\s+");
        this.regexTrailingWhitespaceTrim = Pattern.compile("^\\s*" + Pattern.quote(wsTrim) + "(" + Pattern.quote(printClose) + "|" + Pattern.quote(executeClose) + "|" + Pattern.quote(commentClose) + ")");
    }
    
    public Syntax() {
        this(Delimiter.COMMENT_OPEN.getSymbol(), 
            Delimiter.COMMENT_CLOSE.getSymbol(), 
            Delimiter.EXECUTE_OPEN.getSymbol(), 
            Delimiter.EXECUTE_CLOSE.getSymbol(), 
            Delimiter.PRINT_OPEN.getSymbol(), 
            Delimiter.PRINT_CLOSE.getSymbol(), 
            Delimiter.WS_TRIM.getSymbol());
    }

    public Pattern getRegexPrintClose() {
        return regexPrintClose;
    }

    public Pattern getRegexExecuteClose() {
        return regexExecuteClose;
    }

    public Pattern getRegexCommentClose() {
        return regexCommentClose;
    }

    public Pattern getRegexStartDelimiters() {
        return regexStartDelimiters;
    }

    public Pattern getRegexLeadingWhitespaceTrim() {
        return regexLeadingWhitespaceTrim;
    }

    public Pattern getRegexTrailingWhitespaceTrim() {
        return regexTrailingWhitespaceTrim;
    }
    
}
