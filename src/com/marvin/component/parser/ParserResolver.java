/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.parser;

import com.marvin.component.parser.support.BigDecimalParser;
import com.marvin.component.parser.support.BooleanParser;
import com.marvin.component.parser.support.ByteParser;
import com.marvin.component.parser.support.CharacterParser;
import com.marvin.component.parser.support.DoubleParser;
import com.marvin.component.parser.support.FloatParser;
import com.marvin.component.parser.support.IdentityParser;
import com.marvin.component.parser.support.IntegerParser;
import com.marvin.component.parser.support.LongParser;
import com.marvin.component.parser.support.ObjectParser;
import com.marvin.component.parser.support.ShortParser;
import com.marvin.component.parser.support.SqlDateParser;
import com.marvin.component.parser.support.SqlTimestampParser;
import com.marvin.component.parser.support.StringParser;
import java.util.Arrays;

/**
 *
 * @author cdi305
 */
public class ParserResolver {

    Parser[] parsers;

    public ParserResolver() {
        this.parsers = new Parser[]{
            new BigDecimalParser(),
            new BooleanParser(),
            new ByteParser(),
            new CharacterParser(),
            new DoubleParser(),
            new FloatParser(),
            new IdentityParser(),
            new IntegerParser(),
            new LongParser(),
            new ObjectParser(),
            new ShortParser(),
            new SqlDateParser(),
            new SqlTimestampParser(),
            new StringParser()
        };
    }
    
    public Parser resolve(Object toParse) {
        return Arrays.stream(parsers).filter((Parser parser)->{
            return Arrays.asList(parser.getTypeKeys()).contains(toParse);
        }).findFirst().get();
    }
    
}
