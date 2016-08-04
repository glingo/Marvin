/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.node.ArgumentsNode;

/**
 * The right hand side to the filter expression.
 *
 * @author Mitchell
 *
 */
public class FilterInvocationExpression implements Expression<Object> {

    private final String filterName;

    private final ArgumentsNode args;

    private final int lineNumber;

    public FilterInvocationExpression(String filterName, ArgumentsNode args, int lineNumber) {
        this.filterName = filterName;
        this.args = args;
        this.lineNumber = lineNumber;
    }

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        // see FilterExpression.java
        throw new UnsupportedOperationException();
    }

    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public ArgumentsNode getArgs() {
        return args;
    }

    public String getFilterName() {
        return filterName;
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

}
