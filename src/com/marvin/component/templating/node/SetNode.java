/*******************************************************************************
 * This file is part of Pebble.
 * <p>
 * Copyright (c) 2014 by Mitchell Bösecke
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.node;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.node.expression.Expression;
import java.io.Writer;

public class SetNode extends AbstractRenderableNode {

    private final String name;

    private final Expression<?> value;

    public SetNode(int lineNumber, String name, Expression<?> value) {
        super(lineNumber);
        this.name = name;
        this.value = value;
    }

    @Override
    public void render(Template self, Writer writer, EvaluationContext context) throws Exception {
        context.getScopeChain().put(name, value.evaluate(self, context));
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public Expression<?> getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
