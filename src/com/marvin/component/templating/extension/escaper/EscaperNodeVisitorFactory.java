package com.marvin.component.templating.extension.escaper;

import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.template.TemplateInterface;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.extension.NodeVisitorFactory;


/**
 * Factory class for creating {@link EscaperNodeVisitor}.
 *
 * @author Thomas Hunziker
 *
 */
public class EscaperNodeVisitorFactory implements NodeVisitorFactory {

    private boolean autoEscaping = true;

    @Override
    public NodeVisitor createVisitor(Template template) {
        return new EscaperNodeVisitor(template, this.autoEscaping);
    }

    public void setAutoEscaping(boolean auto) {
        autoEscaping = auto;
    }


}
