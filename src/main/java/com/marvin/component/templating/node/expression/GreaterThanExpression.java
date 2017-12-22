package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.node.operator.OperatorUtils;

public class GreaterThanExpression extends BinaryExpression<Boolean> {

    @Override
    public Boolean evaluate(Template self, EvaluationContext context) throws Exception {
        return OperatorUtils.gt(getLeftExpression().evaluate(self, context),
                getRightExpression().evaluate(self, context));
    }
}
