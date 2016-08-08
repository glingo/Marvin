package com.marvin.component.templating.extension.debug;

import com.marvin.component.templating.extension.AbstractExtension;
import com.marvin.component.templating.extension.NodeVisitorFactory;
import java.util.ArrayList;
import java.util.List;

public class DebugExtension extends AbstractExtension {

    private final PrettyPrintNodeVisitorFactory prettyPrinter = new PrettyPrintNodeVisitorFactory();

    @Override
    public List<NodeVisitorFactory> getNodeVisitors() {
        List<NodeVisitorFactory> visitors = new ArrayList<>();
        visitors.add(prettyPrinter);
        return visitors;
    }

    @Override
    public String toString() {
        return prettyPrinter.toString();
    }
}
