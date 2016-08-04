/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.IncludeNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.node.expression.Expression;
import com.marvin.component.templating.node.expression.MapExpression;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;

public class IncludeTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {

        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip over the 'include' token
        stream.next();

        Expression<?> includeExpression = parser.getExpressionParser().parseExpression();

        Token current = stream.current();
        MapExpression mapExpression = null;

        // We check if there is an optional 'with' parameter on the include tag.
        if (current.getType().equals(Type.NAME) && current.getValue().equals("with")) {

            // Skip over 'with'
            stream.next();

            Expression<?> parsedExpression = parser.getExpressionParser().parseExpression();

            if (parsedExpression instanceof MapExpression) {
                mapExpression = (MapExpression) parsedExpression;
            } else {
                String msg = String.format("Unexpected expression '%1s' at line %s in file %s.", parsedExpression .getClass().getCanonicalName(), token.getLineNumber(), stream.getFilename());
                throw new Exception(msg);
            }

        }

        stream.expect(Type.EXECUTE_END);

        return new IncludeNode(lineNumber, includeExpression, mapExpression);
    }

    @Override
    public String getTag() {
        return "include";
    }
}
