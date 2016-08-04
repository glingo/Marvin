/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.AutoEscapeNode;
import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.parser.StoppingCondition;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;

public class AutoEscapeTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {
        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        String strategy = null;
        boolean active = true;

        // skip over the 'autoescape' token
        stream.next();

        // did user specify active boolean?
        if (stream.current().test(Type.NAME)) {
            active = Boolean.parseBoolean(stream.current().getValue());
            stream.next();
        }

        // did user specify a strategy?
        if (stream.current().test(Type.STRING)) {
            strategy = stream.current().getValue();
            stream.next();
        }

        stream.expect(Type.EXECUTE_END);

        // now we parse the block body
        BodyNode body = parser.subparse((Token token1) -> token1.test(Type.NAME, "endautoescape"));

        // skip the 'endautoescape' token
        stream.next();

        stream.expect(Type.EXECUTE_END);

        return new AutoEscapeNode(lineNumber, body, active, strategy);
    }

    @Override
    public String getTag() {
        return "autoescape";
    }
}
