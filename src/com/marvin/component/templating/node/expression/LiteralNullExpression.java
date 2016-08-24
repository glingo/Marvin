package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;

public class LiteralNullExpression implements Expression<Object> {

    private final int lineNumber;

    public LiteralNullExpression(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        return null;
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

}
