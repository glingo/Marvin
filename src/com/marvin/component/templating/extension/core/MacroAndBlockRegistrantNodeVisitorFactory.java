package com.marvin.component.templating.extension.core;

import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.NodeVisitor;
import com.marvin.component.templating.extension.NodeVisitorFactory;

/**
 * Implementation of {@link NodeVisitorFactory} to handle
 * {@link MacroAndBlockRegistrantNodeVisitor}.
 *
 * @author hunziker
 *
 */
public class MacroAndBlockRegistrantNodeVisitorFactory implements NodeVisitorFactory {

    @Override
    public NodeVisitor createVisitor(Template template) {
        return new MacroAndBlockRegistrantNodeVisitor((Template)template);
    }

}
