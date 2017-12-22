package com.marvin.component.templating.node;

import com.marvin.component.templating.extension.NodeVisitor;

public interface Node {

    void accept(NodeVisitor visitor);

}
