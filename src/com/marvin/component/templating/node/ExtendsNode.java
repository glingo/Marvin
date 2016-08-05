/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.node;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.node.expression.Expression;
import java.io.Writer;


public class ExtendsNode extends AbstractRenderableNode {

    Expression<?> parentExpression;

    public ExtendsNode(int lineNumber, Expression<?> parentExpression) {
        super(lineNumber);
        this.parentExpression = parentExpression;
    }

    @Override
    public void render(final Template self, Writer writer, final EvaluationContext context) throws Exception {
        self.setParent(context, (String) parentExpression.evaluate(self, context));
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public Expression<?> getParentExpression() {
        return parentExpression;
    }
}
