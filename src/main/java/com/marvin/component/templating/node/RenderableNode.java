package com.marvin.component.templating.node;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.template.Template;
import java.io.Writer;

public interface RenderableNode extends Node {

    void render(Template self, Writer writer, EvaluationContext context) throws Exception;
}
