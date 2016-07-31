package org.slieb.jscodebuilder.node;

import com.google.common.base.Preconditions;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class IfNodeBuilder {

    private Node conditionNode, ifBlockNode, elseBlockNode;

    public IfNodeBuilder withCondition(Node conditionNode) {
        this.conditionNode = conditionNode;
        return this;
    }

    public IfNodeBuilder withIfBlockNode(Node conditionNode) {
        this.ifBlockNode = conditionNode;
        return this;
    }

    public IfNodeBuilder withElseBlockNode(Node conditionNode) {
        this.elseBlockNode = conditionNode;
        return this;
    }

    public Node build() {
        Preconditions.checkNotNull(conditionNode);
        Preconditions.checkNotNull(ifBlockNode);

        if (elseBlockNode != null) {
            return new Node(Token.IF, conditionNode, ifBlockNode, elseBlockNode);
        } else {
            return new Node(Token.IF, conditionNode, ifBlockNode);
        }
    }
}
