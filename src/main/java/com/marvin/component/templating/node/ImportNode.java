package com.marvin.component.templating.node;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.node.expression.Expression;
import java.io.Writer;

public class ImportNode extends AbstractRenderableNode {

    private final Expression<?> importExpression;

    public ImportNode(int lineNumber, Expression<?> importExpression) {
        super(lineNumber);
        this.importExpression = importExpression;
    }

    @Override
    public void render(Template self, Writer writer, EvaluationContext context) throws Exception {
        self.importTemplate(context, (String) importExpression.evaluate(self, context));
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public Expression<?> getImportExpression() {
        return importExpression;
    }

}
