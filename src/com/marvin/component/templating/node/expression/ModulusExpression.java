package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.node.operator.OperatorUtils;

public class ModulusExpression extends BinaryExpression<Object> {

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        return OperatorUtils.mod(getLeftExpression().evaluate(self, context),
                getRightExpression().evaluate(self, context));
    }
}
