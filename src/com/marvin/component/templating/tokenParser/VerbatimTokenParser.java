/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.Token;

/**
 * This is just a dummy class to point developers into the right direction; the
 * verbatim tag had to be implemented directly into the lexer.
 *
 * @author mbosecke
 *
 */
public class VerbatimTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {

        throw new UnsupportedOperationException();
    }

    @Override
    public String getTag() {
        return "verbatim";
    }
}
