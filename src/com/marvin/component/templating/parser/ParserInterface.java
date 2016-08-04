/*******************************************************************************
 * This file is part of Pebble.
 * <p>
 * Copyright (c) 2014 by Mitchell Bösecke
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.parser;

import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.RootNode;
import com.marvin.component.templating.token.TokenStream;

public interface ParserInterface {

    RootNode parse(TokenStream stream) throws Exception;

    BodyNode subparse() throws Exception;

    /**
     * Provides the stream of tokens which ultimately need to be "parsed" into
     * Nodes.
     *
     * @return TokenStream
     */
    TokenStream getStream();

    /**
     * Parses the existing TokenStream, starting at the current Token, and
     * ending when the stopCondition is fullfilled.
     *
     * @param stopCondition The condition to stop parsing a segment of the template.
     * @return A node representing the parsed section
     * @throws Exception Thrown if an error occurs.
     */
    BodyNode subparse(StoppingCondition stopCondition) throws Exception;

    ExpressionParser getExpressionParser();

    String peekBlockStack();

    String popBlockStack();

    void pushBlockStack(String blockName);

}
