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
