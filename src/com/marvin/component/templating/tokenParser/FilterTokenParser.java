package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.PrintNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.node.expression.Expression;
import com.marvin.component.templating.node.expression.FilterExpression;
import com.marvin.component.templating.node.expression.RenderableNodeExpression;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses the "filter" tag. It has nothing to do with implementing normal
 * filters.
 */
public class FilterTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {
        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip the 'filter' token
        stream.next();

        List<Expression<?>> filterInvocationExpressions = new ArrayList<>();

        filterInvocationExpressions.add(parser.getExpressionParser().parseFilterInvocationExpression());

        while(stream.current().test(Type.OPERATOR, "|")){
            // skip the '|' token
            stream.next();
            filterInvocationExpressions.add(parser.getExpressionParser().parseFilterInvocationExpression());
        }

        stream.expect(Type.EXECUTE_END);

        BodyNode body = parser.subparse((Token subToken) -> subToken.test(Type.NAME, "endfilter"));

        stream.next();
        stream.expect(Type.EXECUTE_END);

        Expression<?> lastExpression = new RenderableNodeExpression(body, stream.current().getLineNumber());

        for(Expression<?> filterInvocationExpression : filterInvocationExpressions){

            FilterExpression filterExpression = new FilterExpression();
            filterExpression.setRight(filterInvocationExpression);
            filterExpression.setLeft(lastExpression);

            lastExpression = filterExpression;
        }

        return new PrintNode(lastExpression, lineNumber);
    }

//    private StoppingCondition endFilter = (Token token) -> token.test(Token.Type.NAME, "endfilter");

    @Override
    public String getTag() {
        return "filter";
    }
}
