package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;

public class LiteralBooleanExpression implements Expression<Boolean> {

    private final Boolean value;

    private final int lineNumber;

    public LiteralBooleanExpression(Boolean value, int lineNumber) {
        this.value = value;
        this.lineNumber = lineNumber;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Boolean evaluate(Template self, EvaluationContext context) throws Exception {
        return value;
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

}
