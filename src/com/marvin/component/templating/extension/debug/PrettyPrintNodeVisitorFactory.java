package com.marvin.component.templating.extension.debug;

import com.marvin.component.templating.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.extension.NodeVisitorFactory;

/**
 * Implementation of {@link NodeVisitorFactory} to create
 * {@link PrettyPrintNodeVisitor}.
 */
public class PrettyPrintNodeVisitorFactory implements NodeVisitorFactory {

    @Override
    public NodeVisitor createVisitor(Template template) {
       return new PrettyPrintNodeVisitor(template);
    }

}
