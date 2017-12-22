package com.marvin.component.parser.support;

import com.marvin.component.parser.Parser;

/**
 * Returns the value as-is (no conversion)
 *
 */
public class IdentityParser implements Parser<Object> {

    @Override
    public Object[] getTypeKeys() {
        return new Object[]{};
    }

    @Override
    public Object parse(Object value) {
        return value;
    }
}
