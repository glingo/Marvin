package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.template.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.node.Node;

public interface Expression<T> extends Node {

    T evaluate(Template self, EvaluationContext context) throws Exception;

    /**
     * Returns the line number on which the expression is defined on.
     *
     * @return the line number on which the expression is defined on.
     */
    int getLineNumber();
}
