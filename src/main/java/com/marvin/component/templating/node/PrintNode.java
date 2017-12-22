package com.marvin.component.templating.node;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.node.expression.Expression;
import com.marvin.component.util.ObjectUtils;
import java.io.Writer;

public class PrintNode extends AbstractRenderableNode {

    private Expression<?> expression;

    public PrintNode(Expression<?> expression, int lineNumber) {
        super(lineNumber);
        this.expression = expression;
    }

    @Override
    public void render(Template self, Writer writer, EvaluationContext context) throws Exception {
        Object var = expression.evaluate(self, context);
        if (var != null) {
            writer.write(ObjectUtils.nullSafeToString(var));
        }
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public Expression<?> getExpression() {
        return expression;
    }

    public void setExpression(Expression<?> expression) {
        this.expression = expression;
    }

}
