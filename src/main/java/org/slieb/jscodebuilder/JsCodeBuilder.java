package org.slieb.jscodebuilder;

import com.google.common.base.Preconditions;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

@SuppressWarnings("WeakerAccess")
public class JsCodeBuilder {

    private final Node root = new Node(Token.SCRIPT);

    public JsCodeBuilder add(Node node) {
        Preconditions.checkNotNull(node);
        root.addChildToBack(node);
        return this;
    }

    public Node build() {
        return root;
    }
}
