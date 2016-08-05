/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.template;

import java.io.Writer;

public interface Block {

    String getName();

    void evaluate(Template self, Writer writer, EvaluationContext context) throws Exception;
}
