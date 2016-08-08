package com.marvin.component.templating.tokenParser;

import com.marvin.component.templating.node.BlockNode;
import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.RenderableNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.Token;
import com.marvin.component.templating.token.TokenStream;
import com.marvin.component.templating.token.Type;

public class BlockTokenParser extends AbstractTokenParser {

    @Override
    public RenderableNode parse(Token token, Parser parser) throws Exception {
        TokenStream stream = parser.getStream();
        int lineNumber = token.getLineNumber();

        // skip over the 'block' token to the name token
        Token blockName = stream.next();

        // expect a name or string for the new block
        if (!blockName.test(Type.NAME) && !blockName.test(Type.STRING)) {

            // we already know an error has occurred but let's just call the
            // typical "expect" method so that we know a proper error
            // message is given to user
            stream.expect(Type.NAME);
        }

        // get the name of the new block
        String name = blockName.getValue();

        // skip over name
        stream.next();

        stream.expect(Type.EXECUTE_END);

        parser.pushBlockStack(name);

        // now we parse the block body
        BodyNode blockBody = parser.subparse((Token token1) -> token1.test(Type.NAME, "endblock"));
        parser.popBlockStack();

        // skip the 'endblock' token
        stream.next();

        // check if user included block name in endblock
        Token current = stream.current();
        if (current.test(Type.NAME, name) || current.test(Type.STRING, name)) {
            stream.next();
        }

        stream.expect(Type.EXECUTE_END);
        return new BlockNode(lineNumber, name, blockBody);
    }

    @Override
    public String getTag() {
        return "block";
    }
}
