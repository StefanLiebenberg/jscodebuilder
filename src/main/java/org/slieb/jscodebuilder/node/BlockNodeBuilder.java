package org.slieb.jscodebuilder.node;

import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayList;
import java.util.List;

public class BlockNodeBuilder {

    private List<Node> statements = new ArrayList<>();

    public BlockNodeBuilder add(Node child) {
        statements.add(child);
        return this;
    }

    public Node build() {
        final Node node = new Node(Token.BLOCK);
        statements.forEach(node::addChildToBack);
        return node;
    }
}
