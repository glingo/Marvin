package com.marvin.component.templating.node;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import java.io.Writer;

public abstract class AbstractRenderableNode implements RenderableNode {

    private int lineNumber;

    @Override
    public abstract void render(Template self, Writer writer, EvaluationContext context) throws Exception;

    @Override
    public abstract void accept(NodeVisitor visitor);

    public AbstractRenderableNode() {
    }

    public AbstractRenderableNode(int lineNumber) {
        this.setLineNumber(lineNumber);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
