package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.node.operator.OperatorUtils;

public class UnaryPlusExpression extends UnaryExpression {

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        return OperatorUtils.unaryPlus(getChildExpression().evaluate(self, context));
    }

}
