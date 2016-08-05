/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.node.RenderableNode;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * This class wraps a {@link RenderableNode} into an expression. This is used by
 * the filter TAG to apply a filter to large chunk of template which is
 * contained within a renderable node.
 *
 * @author mbosecke
 *
 */
public class RenderableNodeExpression extends UnaryExpression {

    private final RenderableNode node;

    private final int lineNumber;

    public RenderableNodeExpression(RenderableNode node, int lineNumber) {
        this.node = node;
        this.lineNumber = lineNumber;
    }

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        Writer writer = new StringWriter();
        try {
            node.render(self, writer, context);
        } catch (IOException e) {
            String msg = String.format("Error occurred while rendering node at line %s in file %s.", this.getLineNumber(), self.getName());
            throw new Exception(msg);
        }
        return writer.toString();
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

}
