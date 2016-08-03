/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.templating.lexer;

import com.marvin.component.templating.lexer.operator.BinaryOperator;
import com.marvin.component.templating.lexer.operator.UnaryOperator;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cdi305
 */
public class Lexer {

    protected Syntax syntax;

    protected Source source;

    protected ArrayList<Token> tokens;

    protected Collection<UnaryOperator> unaryOperators;

    protected Collection<BinaryOperator> binaryOperators;

    protected Pattern regexOperators;

    public Lexer(Syntax syntax, Collection<UnaryOperator> unaryOperators, Collection<BinaryOperator> binaryOperators) {
        this.syntax = syntax;
        this.unaryOperators = unaryOperators;
        this.binaryOperators = binaryOperators;
    }

    public void tokenize(Reader reader, String name) throws IOException {
        this.tokens = new ArrayList();

        this.source = new Source(reader, name);

    }

    public void a() {
//        String source = "";

        buildOperatorRegex();

        Matcher matcher = this.syntax.getRegexStartDelimiters().matcher(source);
        boolean match = matcher.find();

        String text = null;
        String startDelimiterToken = null;

        // if we didn't find another start delimiter, the text
        // token goes all the way to the end of the template.
        if (!match) {
            text = source.toString();
            source.advance(source.length());
        } else {
            text = source.substring(matcher.start());
            startDelimiterToken = source.substring(matcher.start(), matcher.end());

            // advance to after the start delimiter
            source.advance(matcher.end());
        }

        Token textToken = pushToken(Type.TEXT, text);

    }

    private Token pushToken(Type type) {
        return pushToken(type, null);
    }

    /**
     * Create a Token of a certain type and value and push it into the list of
     * tokens that we are maintaining. `
     *
     * @param type The type of token we are creating
     * @param value The value of the new token
     */
    private Token pushToken(Type type, String value) {

        // ignore empty text tokens
        if (type.equals(Type.TEXT) && (value == null || "".equals(value))) {
            return null;
        }

        Token result = new Token(type, value, source.getLineNumber());

        this.tokens.add(result);

        return result;
    }

    /**
     * Retrieves the operators (both unary and binary) from the PebbleEngine and
     * then dynamically creates one giant regular expression to detect for the
     * existence of one of these operators.
     *
     * @return Pattern The regular expression used to find an operator
     */
    private void buildOperatorRegex() {

        List<String> operators = new ArrayList<>();

        unaryOperators.stream().forEach((operator) -> {
            operators.add(operator.getSymbol());
        });

        binaryOperators.stream().forEach((operator) -> {
            operators.add(operator.getSymbol());
        });

        StringBuilder regex = new StringBuilder("^");

        boolean isFirst = true;

        for (String operator : operators) {
            if (isFirst) {
                isFirst = false;
            } else {
                regex.append("|");
            }
            regex.append(Pattern.quote(operator));

            char nextChar = operator.charAt(operator.length() - 1);
            if (Character.isLetter(nextChar) || Character.getType(nextChar) == Character.LETTER_NUMBER) {
                regex.append("(?![a-zA-Z])");
            }
        }

        this.regexOperators = Pattern.compile(regex.toString());
    }

}
