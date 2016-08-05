/*******************************************************************************
 * This file is part of Pebble.
 * <p>
 * Copyright (c) 2014 by Mitchell Bösecke
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.scope.ScopeChain;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;


public class ContextVariableExpression implements Expression<Object> {

    protected final String name;

    private final int lineNumber;

    public ContextVariableExpression(String name, int lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public Object evaluate(Template self, EvaluationContext context) throws Exception {
        ScopeChain scopeChain = context.getScopeChain();
        Object result = scopeChain.get(name);
        if (result == null && context.isStrictVariables() && !scopeChain.containsKey(name)) {
            String msg = String.format("Root attribute [%s] does not exist or can not be accessed and strict variables is set to true at line %s in file %s.", this.name, this.lineNumber, self.getName());
            throw new Exception(msg);
        }
        return result;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

}
