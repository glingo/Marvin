/*******************************************************************************
 * This file is part of Pebble.
 *
 * Copyright (c) 2014 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.marvin.component.templating.extension.core;

import com.marvin.component.templating.template.Template;
import com.marvin.component.templating.extension.AbstractNodeVisitor;
import com.marvin.component.templating.node.BlockNode;
import com.marvin.component.templating.node.MacroNode;

public class MacroAndBlockRegistrantNodeVisitor extends AbstractNodeVisitor {

    public MacroAndBlockRegistrantNodeVisitor(Template template) {
        super(template);
    }

    @Override
    public void visit(BlockNode node) {
        this.getTemplate().registerBlock(node.getBlock());
        super.visit(node);
    }

    @Override
    public void visit(MacroNode node) {
        try {
            this.getTemplate().registerMacro(node.getMacro());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.visit(node);
    }
}
