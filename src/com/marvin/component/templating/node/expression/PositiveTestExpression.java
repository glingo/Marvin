/*******************************************************************************
 * This file is part of Pebble.
 * <p>
 * Copyright (c) 2014 by Mitchell Bösecke
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.Template;
import com.marvin.component.templating.extension.Test;
import com.marvin.component.templating.extension.core.DefinedTest;
import com.marvin.component.templating.node.ArgumentsNode;
import com.marvin.component.templating.node.TestInvocationExpression;
import java.util.Map;
import javax.management.AttributeNotFoundException;

public class PositiveTestExpression extends BinaryExpression<Object> {

    private Test cachedTest;

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {

        TestInvocationExpression testInvocation = (TestInvocationExpression) getRightExpression();
        ArgumentsNode args = testInvocation.getArgs();

        if (cachedTest == null) {
            String testName = testInvocation.getTestName();

            cachedTest = context.getExtensionRegistry().getTest(testInvocation.getTestName());

            if (cachedTest == null) {
                String msg = String.format("Test [%s] does not exist at line %s  in file %s.", testName, this.getLineNumber(), self.getName());
                throw new Exception(msg);
            }
        }
        Test test = cachedTest;

        Map<String, Object> namedArguments = args.getArgumentMap(self, context, test);

        // This check is not nice, because we use instanceof. However this is
        // the only test which should not fail in strict mode, when the variable
        // is not set, because this method should exactly test this. Hence a
        // generic solution to allow other tests to reuse this feature make no
        // sense.
        if (test instanceof DefinedTest) {
            Object input = null;
            try {
                input = getLeftExpression().evaluate(self, context);
            } catch (AttributeNotFoundException e) {
                input = null;
            }
            return test.apply(input, namedArguments);
        } else {
            return test.apply(getLeftExpression().evaluate(self, context), namedArguments);
        }

    }
}
