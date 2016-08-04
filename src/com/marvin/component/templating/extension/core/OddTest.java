/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.extension.core;

import com.marvin.component.templating.extension.Test;
import java.util.List;
import java.util.Map;

public class OddTest implements Test {

    @Override
    public List<String> getArgumentNames() {
        return null;
    }

    @Override
    public boolean apply(Object input, Map<String, Object> args) {
        if (input == null) {
            throw new IllegalArgumentException("Can not pass null value to \"odd\" test.");
        }
        EvenTest evenTest = new EvenTest();
        return !evenTest.apply(input, args);
    }
}
