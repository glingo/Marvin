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
