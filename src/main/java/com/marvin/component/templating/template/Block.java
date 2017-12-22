package com.marvin.component.templating.template;

import com.marvin.component.templating.EvaluationContext;
import java.io.Writer;

public interface Block {

    String getName();

    void evaluate(Template self, Writer writer, EvaluationContext context) throws Exception;
}
