package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.template.Template;

public class NegativeTestExpression extends PositiveTestExpression {

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        return !((Boolean) super.evaluate(self, context));
    }
}
