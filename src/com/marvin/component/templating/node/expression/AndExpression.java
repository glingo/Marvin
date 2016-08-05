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

public class AndExpression extends BinaryExpression<Boolean> {

    @Override
    public Boolean evaluate(Template self, EvaluationContext context) throws Exception {
        Expression<Boolean> left = (Expression<Boolean>) getLeftExpression();
        Expression<Boolean> right = (Expression<Boolean>) getRightExpression();
        return left.evaluate(self, context) && right.evaluate(self, context);
    }
}
