package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.node.SetNode;
import com.marvin.component.templating.node.expression.Expression;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;

public class SetTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {
        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip the 'set' token
        stream.next();

        String name = parser.getExpressionParser().parseNewVariableName();

        stream.expect(Type.PUNCTUATION, "=");

        Expression<?> value = parser.getExpressionParser().parseExpression();

        stream.expect(Type.EXECUTE_END);

        return new SetNode(lineNumber, name, value);
    }

    @Override
    public String getTag() {
        return "set";
    }
}
