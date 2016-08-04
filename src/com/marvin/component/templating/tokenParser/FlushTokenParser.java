/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.FlushNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;

public class FlushTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {

        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip over the 'flush' token
        stream.next();

        stream.expect(Type.EXECUTE_END);

        return new FlushNode(lineNumber);
    }

    @Override
    public String getTag() {
        return "flush";
    }
}
