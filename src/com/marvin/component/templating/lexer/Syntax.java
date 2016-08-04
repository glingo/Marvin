/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.lexer;

import com.marvin.component.templating.token.Delimiter;
import java.util.regex.Pattern;

/**
 *
 * @author cdi305
 */
public class Syntax {
    
    private static final String POSSIBLE_NEW_LINE = "(\r\n|\n\r|\r|\n|\u0085|\u2028|\u2029)?";
    
    private String commentOpen;
    
    private String commentClose;
    
    private String executeOpen;
    
    private String executeClose;
    
    private String printOpen;
    
    private String printClose;
    
    private String wsTrim;
    
    
    private final Pattern regexPrintClose;

    private final Pattern regexExecuteClose;

    private final Pattern regexCommentClose;

    private final Pattern regexStartDelimiters;

    private final Pattern regexLeadingWhitespaceTrim;

    private final Pattern regexTrailingWhitespaceTrim;

    
    public Syntax() {
        this(Delimiter.COMMENT, Delimiter.EXECUTE, Delimiter.PRINT, "-");
    }
    
    public Syntax(Delimiter comment, Delimiter execute, Delimiter print, String wsTrim) {
        this(comment.getOpen(), comment.getClose(), execute.getOpen(), execute.getClose(), print.getOpen(), print.getClose(), wsTrim);
    }
    
    public Syntax(final String commentOpen, final String commentClose, final String executeOpen, final String executeClose, final String printOpen, final String printClose, final String wsTrim) {
        
        this.commentOpen = commentOpen;
        this.commentClose = commentClose;
        this.executeOpen = executeOpen;
        this.executeClose = executeClose;
        this.printOpen = printOpen;
        this.printClose = printClose;
        this.wsTrim = wsTrim;
        
        this.regexPrintClose = Pattern.compile("^\\s*" + Pattern.quote(wsTrim) + "?" + Pattern.quote(printClose) + POSSIBLE_NEW_LINE);
        this.regexExecuteClose = Pattern.compile("^\\s*" + Pattern.quote(wsTrim) + "?" + Pattern.quote(executeClose) + POSSIBLE_NEW_LINE);
        this.regexCommentClose = Pattern.compile(Pattern.quote(commentClose) + POSSIBLE_NEW_LINE);
        
        this.regexStartDelimiters = Pattern.compile(Pattern.quote(printOpen) + "|" + Pattern.quote(executeOpen) + "|" + Pattern.quote(commentOpen));
        this.regexLeadingWhitespaceTrim = Pattern.compile(Pattern.quote(wsTrim) + "\\s+");
        this.regexTrailingWhitespaceTrim = Pattern.compile("^\\s*" + Pattern.quote(wsTrim) + "(" + Pattern.quote(printClose) + "|" + Pattern.quote(executeClose) + "|" + Pattern.quote(commentClose) + ")");
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

    public String getCommentOpen() {
        return commentOpen;
    }

    public void setCommentOpen(String commentOpen) {
        this.commentOpen = commentOpen;
    }

    public String getCommentClose() {
        return commentClose;
    }

    public void setCommentClose(String commentClose) {
        this.commentClose = commentClose;
    }

    public String getExecuteOpen() {
        return executeOpen;
    }

    public void setExecuteOpen(String executeOpen) {
        this.executeOpen = executeOpen;
    }

    public String getExecuteClose() {
        return executeClose;
    }

    public void setExecuteClose(String executeClose) {
        this.executeClose = executeClose;
    }

    public String getPrintOpen() {
        return printOpen;
    }

    public void setPrintOpen(String printOpen) {
        this.printOpen = printOpen;
    }

    public String getPrintClose() {
        return printClose;
    }

    public void setPrintClose(String printClose) {
        this.printClose = printClose;
    }

    public String getWsTrim() {
        return wsTrim;
    }

    public void setWsTrim(String wsTrim) {
        this.wsTrim = wsTrim;
    }
    
    
    
}
