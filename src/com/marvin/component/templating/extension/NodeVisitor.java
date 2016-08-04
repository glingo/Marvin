/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.extension;

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

/**
 * Will visit all the nodes of the AST provided by the parser. The NodeVisitor
 * is responsible for the navigating the tree, it can extend AbstractNodeVisitor
 * for help with this.
 *
 * A NodeVisitor can still use method overloading to visit expressions (it's
 * just not required).
 *
 * <p>
 * The implementor does not need to make sure that the implementation is thread-safe.
 *
 * @author Mitchell
 *
 */
public interface NodeVisitor {

    /**
     * Default method invoked with unknown nodes such as nodes provided by user
     * extensions.
     *
     * @param node Node to visit
     */
    void visit(Node node);

    /*
     * OVERLOADED NODES (keep alphabetized)
     */
    void visit(ArgumentsNode node);

    void visit(AutoEscapeNode node);

    void visit(BlockNode node);

    void visit(BodyNode node);

    void visit(ExtendsNode node);

    void visit(FlushNode node);

    void visit(ForNode node);

    void visit(IfNode node);

    void visit(ImportNode node);

    void visit(IncludeNode node);

    void visit(MacroNode node);

    void visit(NamedArgumentNode node);

    void visit(ParallelNode node);

    void visit(PositionalArgumentNode node);

    void visit(PrintNode node);

    void visit(RootNode node);

    void visit(SetNode node);

    void visit(TextNode node);

}
