/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.ArgumentsNode;
import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.MacroNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.parser.StoppingCondition;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;


public class MacroTokenParser extends AbstractTokenParser {

    private final StoppingCondition decideMacroEnd = (Token token) -> token.test(Type.NAME, "endmacro");

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {

        TokenStream stream = parser.getStream();

        // skip over the 'macro' token
        stream.next();

        String macroName = stream.expect(Type.NAME).getValue();

        ArgumentsNode args = parser.getExpressionParser().parseArguments(true);

        stream.expect(Type.EXECUTE_END);

        // parse the body
        BodyNode body = parser.subparse(decideMacroEnd);

        // skip the 'endmacro' token
        stream.next();

        stream.expect(Type.EXECUTE_END);

        return new MacroNode(macroName, args, body);
    }

    @Override
    public String getTag() {
        return "macro";
    }
}
