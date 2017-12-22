package com.marvin.component.parser.support;

import com.marvin.component.parser.Parser;
import com.marvin.component.parser.Parser;

/**
 * Convert to a boolean by parsing the value as a string
 *
 */
public class BooleanParser implements Parser<Boolean> {

    @Override
    public Object[] getTypeKeys() {
        return new Object[]{
            Boolean.class,
            Boolean.TYPE,
            Boolean.class.getName()
        };
    }

    @Override
    public Boolean parse(Object value) {
        
        if (value == null) {
            return null;
        }
        
        if(value instanceof Boolean) {
            return (Boolean) value;
        } else {
            String v = value.toString();
            if (v.trim().length() != 0) {
                return Boolean.parseBoolean(v);
            }
        }
        
        return null;
    }
}
