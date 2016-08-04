/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.extension.debug;

import com.marvin.component.templating.Template;
import com.marvin.component.templating.extension.AbstractNodeVisitor;
import com.marvin.component.templating.node.ArgumentsNode;
import com.marvin.component.templating.node.BlockNode;
import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.FlushNode;
import com.marvin.component.templating.node.ForNode;
import com.marvin.component.templating.node.IfNode;
import com.marvin.component.templating.node.ImportNode;
import com.marvin.component.templating.node.IncludeNode;
import com.marvin.component.templating.node.NamedArgumentNode;
import com.marvin.component.templating.node.Node;
import com.marvin.component.templating.node.ParallelNode;
import com.marvin.component.templating.node.PrintNode;
import com.marvin.component.templating.node.RootNode;
import com.marvin.component.templating.node.SetNode;
import com.marvin.component.templating.node.TestInvocationExpression;
import com.marvin.component.templating.node.TextNode;
import com.marvin.component.templating.node.expression.BinaryExpression;
import com.marvin.component.templating.node.expression.ContextVariableExpression;
import com.marvin.component.templating.node.expression.FilterInvocationExpression;
import com.marvin.component.templating.node.expression.FunctionOrMacroInvocationExpression;
import com.marvin.component.templating.node.expression.GetAttributeExpression;
import com.marvin.component.templating.node.expression.ParentFunctionExpression;
import com.marvin.component.templating.node.expression.TernaryExpression;
import com.marvin.component.templating.node.expression.UnaryExpression;

public class PrettyPrintNodeVisitor extends AbstractNodeVisitor {

    public PrettyPrintNodeVisitor(Template template) {
        super(template);
    }

    private StringBuilder output = new StringBuilder();

    private int level = 0;

    private void write(String message) {
        for (int i = 0; i < level - 1; i++) {
            output.append("| ");
        }
        if (level > 0) {
            output.append("|-");
        }
        output.append(message.toUpperCase()).append("\n");
    }

    @Override
    public String toString() {
        return output.toString();
    }

    /**
     * Default method used for unknown nodes such as nodes from a user provided
     * extension.
     * @param node
     */
    @Override
    public void visit(Node node) {
        write("unknown");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(BodyNode node) {
        write("body");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(IfNode node) {
        write("if");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(ForNode node) {
        write("for");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(BinaryExpression<?> node) {
        write("binary");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(UnaryExpression node) {
        write("unary");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(ContextVariableExpression node) {
        write(String.format("context variable [%s]", node.getName()));
        level++;
        super.visit(node);
        level--;
    }

    public void visit(FilterInvocationExpression node) {
        write("filter");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(FunctionOrMacroInvocationExpression node) {
        write("function or macro");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(GetAttributeExpression node) {
        write("get attribute");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(NamedArgumentNode node) {
        write("named argument");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(ArgumentsNode node) {
        write("named arguments");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(ParentFunctionExpression node) {
        write("parent function");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(TernaryExpression node) {
        write("ternary");
        level++;
        super.visit(node);
        level--;
    }

    public void visit(TestInvocationExpression node) {
        write("test");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(BlockNode node) {
        write(String.format("block [%s]", node.getName()));
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(FlushNode node) {
        write("flush");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(ImportNode node) {
        write("import");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(IncludeNode node) {
        write("include");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(ParallelNode node) {
        write("parallel");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(PrintNode node) {
        write("print");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(RootNode node) {
        write("root");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(SetNode node) {
        write("set");
        level++;
        super.visit(node);
        level--;
    }

    @Override
    public void visit(TextNode node) {
        String text = new String(node.getData());
        String preview = text.length() > 10 ? text.substring(0, 10) + "..." : text;
        write(String.format("text [%s]", preview));
        level++;
        super.visit(node);
        level--;
    }
}
