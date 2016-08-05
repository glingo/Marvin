package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.core.RangeFunction;
import com.marvin.component.templating.node.ArgumentsNode;
import com.marvin.component.templating.node.PositionalArgumentNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Expression which implements the range function.
 *
 * @author Eric Bussieres
 *
 */
public class RangeExpression extends BinaryExpression<Object> {

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        List<PositionalArgumentNode> positionalArgs = new ArrayList<>();
        positionalArgs.add(new PositionalArgumentNode(getLeftExpression()));
        positionalArgs.add(new PositionalArgumentNode(getRightExpression()));

        ArgumentsNode arguments = new ArgumentsNode(positionalArgs, null, this.getLineNumber());
        FunctionOrMacroInvocationExpression function = new FunctionOrMacroInvocationExpression(
                RangeFunction.FUNCTION_NAME, arguments, this.getLineNumber());

        return function.evaluate(self, context);
    }

}