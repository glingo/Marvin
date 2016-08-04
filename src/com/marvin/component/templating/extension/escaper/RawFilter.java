/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.extension.escaper;

import com.marvin.component.templating.extension.Filter;
import java.util.List;
import java.util.Map;

public class RawFilter implements Filter {

    @Override
    public List<String> getArgumentNames() {
        return null;
    }

    @Override
    public Object apply(Object inputObject, Map<String, Object> args) {
        if(inputObject instanceof String){
            return new SafeString((String) inputObject);
        }
        return inputObject;
    }

}
