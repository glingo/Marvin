package com.marvin.component.templating.template;

import com.marvin.component.templating.extension.NamedArguments;
import java.util.Map;

public interface Macro extends NamedArguments {

    String getName();

    String call(Template self, EvaluationContext context, Map<String, Object> args) throws Exception;
}
