package org.slieb.jscodebuilder;

import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import org.slieb.jscodebuilder.node.BlockNodeBuilder;
import org.slieb.jscodebuilder.node.IfNodeBuilder;

import java.io.IOException;
import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
public class NodeFactory {

    private static final ContentParser contentParser = new ContentParser();

    private NodeFactory() {}

    public static Node raw(String content) throws IOException {
        final SourceFile src = SourceFile.fromCode("NodeFactory:raw", content);
        final Node parse = contentParser.parse(src);
        return parse;
    }

    public static Node nameNode(final String name) {return Node.newString(Token.NAME, name);}

    public static Node nameNode(final String name, Node child) {
        final Node node = nameNode(name);
        node.addChildToFront(child);
        return node;
    }

    public static Node variables(final Node... nodes) {
        Node n = new Node(Token.VAR);
        Arrays.stream(nodes).forEach(n::addChildToBack);
        return n;
    }

    public static Node variable(final String name) {
        return variables(nameNode(name));
    }

    public static Node variable(String qualifiedName, Node value) {
        return variables(nameNode(qualifiedName, value));
    }

    public static Node ifStatement(final Node conditionNode, final Node ifBlockNode, final Node elseBlockNode) {
        return ifStatement()
                .withCondition(conditionNode)
                .withIfBlockNode(ifBlockNode)
                .withElseBlockNode(elseBlockNode)
                .build();
    }

    public static Node exprNode(Node value) {
        return new Node(Token.EXPR_RESULT, value);
    }

    public static Node quotedString(final String value) {
        final Node strNode = Node.newString(value);
        strNode.setQuotedString();
        return strNode;
    }

    public static Node booleanNode(final boolean bool) {
        return new Node(bool ? Token.TRUE : Token.FALSE);
    }

    public static Node assignNode(final String x, final Node e) {
        return new Node(Token.ASSIGN, nameNode(x), e);
    }

    public static Node numberNode(final int i) {
        return Node.newNumber(i);
    }

    public static Node jsDoc(final String s) {
        return new Node(Token.EMPTY);
    }

    public static IfNodeBuilder ifStatement() {
        return new IfNodeBuilder();
    }

    public static BlockNodeBuilder blockNode() {
        return new BlockNodeBuilder();
    }

    public static Node debugger() {
        return new Node(Token.DEBUGGER);
    }
}
