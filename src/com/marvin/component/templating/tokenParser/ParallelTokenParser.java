package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.ParallelNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.parser.StoppingCondition;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;

public class ParallelTokenParser extends AbstractTokenParser {

    private final StoppingCondition decideParallelEnd = (Token token) -> token.test(Type.NAME, "endparallel");

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {
        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip the 'parallel' token
        stream.next();

        stream.expect(Type.EXECUTE_END);

        BodyNode body = parser.subparse(decideParallelEnd);

        // skip the 'endparallel' token
        stream.next();

        stream.expect(Type.EXECUTE_END);
        return new ParallelNode(lineNumber, body);
    }

    @Override
    public String getTag() {
        return "parallel";
    }
}
