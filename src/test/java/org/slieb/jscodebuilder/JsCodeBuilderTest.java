package org.slieb.jscodebuilder;

import com.google.javascript.rhino.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsCodeBuilderTest {

    private JsCodeBuilder cb;

    @Before
    public void setUp() throws Exception {
        cb = new JsCodeBuilder();
    }

    @Test
    public void shouldBuildRootNode() throws Exception {
        final Node node = cb.build();
        Assert.assertNotNull(node);
        Assert.assertTrue(node.isScript());
        Assert.assertFalse(node.hasChildren());
    }

    @Test
    public void shouldBuildVariableNodeWithValue() throws Exception {
        final Node expected = NodeFactory.raw("var object = 'value';");
        final Node node = cb.add(NodeFactory.variable("object", NodeFactory.quotedString("value"))).build();
        Assert.assertTrue(expected.isEquivalentTo(node));
    }

    @Test
    public void shouldBuildVariableNodeWithoutValue() throws Exception {
        final Node expected = NodeFactory.raw("var object;");
        final Node node = cb.add(NodeFactory.variable("object")).build();
        Assert.assertTrue(expected.isEquivalentTo(node));
    }

    @Test
    public void shouldBuildIfElseStatementWithVariableAssignment() throws Exception {
        final Node expected = NodeFactory.raw("var x,y;if(true) { x = 'oo'; y = 1; } else { x = 'e'; y =10; }");
        final Node node = cb.add(NodeFactory.variables(NodeFactory.nameNode("x"), NodeFactory.nameNode("y")))
                            .add(NodeFactory.ifStatement(NodeFactory.booleanNode(true),
                                                         NodeFactory.blockNode()
                                                                    .add(NodeFactory.exprNode(NodeFactory.assignNode("x", NodeFactory.quotedString("oo"))))
                                                                    .add(NodeFactory.exprNode(NodeFactory.assignNode("y", NodeFactory.numberNode(1))))
                                                                    .build(),
                                                         NodeFactory.blockNode()
                                                                    .add(NodeFactory.exprNode(NodeFactory.assignNode("x", NodeFactory.quotedString("e"))))
                                                                    .add(NodeFactory.exprNode(NodeFactory.assignNode("y", NodeFactory.numberNode(10))))
                                                                    .build()
                            )).build();
        Assert.assertTrue(expected.isEquivalentTo(node));
    }
}
