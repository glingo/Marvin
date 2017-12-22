package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.ExtendsNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.node.expression.Expression;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;

public class ExtendsTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {
        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip the 'extends' token
        stream.next();

        Expression<?> parentTemplateExpression = parser.getExpressionParser().parseExpression();

        stream.expect(Type.EXECUTE_END);
        return new ExtendsNode(lineNumber, parentTemplateExpression);
    }

    @Override
    public String getTag() {
        return "extends";
    }
}
