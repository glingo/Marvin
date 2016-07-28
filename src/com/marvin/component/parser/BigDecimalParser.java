package com.marvin.component.parser;

import com.marvin.component.parser.Parser;
import java.math.BigDecimal;

/**
 * Convert to a {@link BigDecimal} by parsing the value as a string
 *
 */
public class BigDecimalParser implements Parser<BigDecimal> {

    @Override
    public Object[] getTypeKeys() {
        return new Object[]{
            BigDecimal.class,
            BigDecimal.class.getName()
        };
    }

    @Override
    public BigDecimal parse(Object value) {
        if (value == null) {
            return null;
        }
        
        if (!(value instanceof BigDecimal)) {
            String v = value.toString();
            if (v.trim().length() != 0) {
                return new BigDecimal(v);
            }
        }
        
        return null;
    }

}
