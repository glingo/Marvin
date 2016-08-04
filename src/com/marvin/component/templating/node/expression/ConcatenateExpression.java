package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.Template;

/**
 * Expression which implements the string concatenation.
 *
 * @author Thomas Hunziker
 *
 */
public class ConcatenateExpression extends BinaryExpression<Object> {

    @Override
    public String evaluate(Template self, EvaluationContext context) throws Exception {

        Object left = getLeftExpression().evaluate(self, context);
        Object right = getRightExpression().evaluate(self, context);
        StringBuilder result = new StringBuilder();
        if (left != null) {
            result.append(left.toString());
        }
        if (right != null) {
            result.append(right.toString());
        }

        return result.toString();
    }

}
