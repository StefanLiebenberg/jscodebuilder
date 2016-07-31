package org.slieb.jscodebuilder;

import com.google.javascript.jscomp.CodePrinter;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.parsing.Config;
import com.google.javascript.rhino.Node;

public class JsCodePrinter {

    private final CompilerOptions OPTIONS;

    {
        OPTIONS = new CompilerOptions();
        OPTIONS.setParseJsDocDocumentation(Config.JsDocParsing.INCLUDE_DESCRIPTIONS_NO_WHITESPACE);
        OPTIONS.setPreserveTypeAnnotations(true);
    }

    public String print(Node node) {
        return new CodePrinter.Builder(node)
                .setCompilerOptions(OPTIONS)
                .setPrettyPrint(true)
                .build();
    }

}
