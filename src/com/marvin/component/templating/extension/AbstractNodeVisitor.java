/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.extension;

import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.node.ArgumentsNode;
import com.marvin.component.templating.node.AutoEscapeNode;
import com.marvin.component.templating.node.BlockNode;
import com.marvin.component.templating.node.BodyNode;
import com.marvin.component.templating.node.ExtendsNode;
import com.marvin.component.templating.node.FlushNode;
import com.marvin.component.templating.node.ForNode;
import com.marvin.component.templating.node.IfNode;
import com.marvin.component.templating.node.ImportNode;
import com.marvin.component.templating.node.IncludeNode;
import com.marvin.component.templating.node.MacroNode;
import com.marvin.component.templating.node.NamedArgumentNode;
import com.marvin.component.templating.node.Node;
import com.marvin.component.templating.node.ParallelNode;
import com.marvin.component.templating.node.PositionalArgumentNode;
import com.marvin.component.templating.node.PrintNode;
import com.marvin.component.templating.node.RootNode;
import com.marvin.component.templating.node.SetNode;
import com.marvin.component.templating.node.TextNode;
import com.marvin.component.templating.node.expression.Expression;
import javafx.util.Pair;

/**
 * A base node visitor that can be extended for the sake of using it's
 * navigational abilities.
 *
 * @author Mitchell
 *
 */
public class AbstractNodeVisitor implements NodeVisitor {

    private final Template template;

    public AbstractNodeVisitor(Template template) {
        this.template = template;
    }

    /**
     * Default method used for unknown nodes such as nodes from a user provided
     * extension.
     */
    @Override
    public void visit(Node node) {
    }

    /*
     * OVERLOADED NODES (keep alphabetized)
     */
    @Override
    public void visit(ArgumentsNode node) {
        if (node.getNamedArgs() != null) {
            for (Node arg : node.getNamedArgs()) {
                arg.accept(this);
            }
        }
        if (node.getPositionalArgs() != null) {
            for (Node arg : node.getPositionalArgs()) {
                arg.accept(this);
            }
        }
    }

    @Override
    public void visit(AutoEscapeNode node) {
        node.getBody().accept(this);
    }

    @Override
    public void visit(BlockNode node) {
        node.getBody().accept(this);
    }

    @Override
    public void visit(BodyNode node) {
        for (Node child : node.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ExtendsNode node) {
        node.getParentExpression().accept(this);
    }

    @Override
    public void visit(FlushNode node) {

    }

    @Override
    public void visit(ForNode node) {
        node.getIterable().accept(this);
        node.getBody().accept(this);
        if (node.getElseBody() != null) {
            node.getElseBody().accept(this);
        }
    }

    @Override
    public void visit(IfNode node) {
        node.getConditionsWithBodies().stream().map((pairs) -> {
            pairs.getKey().accept(this);
            return pairs;
        }).forEach((pairs) -> {
            pairs.getValue().accept(this);
        });
        if (node.getElseBody() != null) {
            node.getElseBody().accept(this);
        }
    }

    @Override
    public void visit(ImportNode node) {
        node.getImportExpression().accept(this);
    }

    @Override
    public void visit(IncludeNode node) {
        node.getIncludeExpression().accept(this);
    }

    @Override
    public void visit(MacroNode node) {
        node.getBody().accept(this);
        node.getArgs().accept(this);
    }

    @Override
    public void visit(NamedArgumentNode node) {
        if (node.getValueExpression() != null) {
            node.getValueExpression().accept(this);
        }
    }

    @Override
    public void visit(ParallelNode node) {
        node.getBody().accept(this);
    }

    @Override
    public void visit(PositionalArgumentNode node) {
        node.getValueExpression().accept(this);
    }

    @Override
    public void visit(PrintNode node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(RootNode node) {
        node.getBody().accept(this);
    }

    @Override
    public void visit(SetNode node) {
        node.getValue().accept(this);
    }

    @Override
    public void visit(TextNode node) {

    }

    protected Template getTemplate() {
        return this.template;
    }

}
