package com.marvin.component.templating.node;

import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.node.expression.Expression;

public class PositionalArgumentNode implements Node {

    private final Expression<?> value;

    public PositionalArgumentNode(Expression<?> value) {
        this.value = value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public Expression<?> getValueExpression() {
        return value;
    }

}
