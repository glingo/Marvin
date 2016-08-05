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

public class FunctionOrMacroNameNode implements Expression<String> {

    private final String name;

    private final int lineNumber;

    public FunctionOrMacroNameNode(String name, int lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }

    @Override
    public String evaluate(Template self, EvaluationContext context) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

}
