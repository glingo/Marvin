package com.marvin.component.templating.node.expression;

import com.marvin.component.templating.EvaluationContext;
import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.node.ArgumentsNode;
import java.io.StringWriter;
import java.io.Writer;

public class BlockFunctionExpression implements Expression<String> {

    private final Expression<?> blockNameExpression;

    private final int lineNumber;

    public BlockFunctionExpression(ArgumentsNode args, int lineNumber) {
        this.blockNameExpression = args.getPositionalArgs().get(0).getValueExpression();
        this.lineNumber = lineNumber;
    }

    @Override
    public String evaluate(Template self, EvaluationContext context) throws Exception {
        Writer writer = new StringWriter();
        String blockName = (String) blockNameExpression.evaluate(self, context);
        try {
            self.block(writer, context, blockName, false);
        } catch (Exception e) {
            String msg = String.format("Could not render block [%s] at line %s in file %s.", blockName, this.getLineNumber(), self.getName());
            throw new Exception(msg);
        }
        return writer.toString();
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

}
