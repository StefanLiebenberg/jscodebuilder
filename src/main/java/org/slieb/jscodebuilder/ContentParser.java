package org.slieb.jscodebuilder;

import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.parsing.Config;
import com.google.javascript.jscomp.parsing.ParserRunner;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.Node;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;

import static com.google.javascript.jscomp.parsing.ParserRunner.createConfig;

@SuppressWarnings("WeakerAccess")
public class ContentParser {

    public static final Config CONFIG =
            createConfig(Config.LanguageMode.ECMASCRIPT3, Config.JsDocParsing.INCLUDE_DESCRIPTIONS_NO_WHITESPACE,
                         null, null, Collections.emptySet());

    private final ErrorReporter errorReporter = IgnoringReporter.INSTANCE;

    public Node parse(final SourceFile src) throws IOException {
        final ParserRunner.ParseResult result = ParserRunner.parse(src, src.getCode(), CONFIG, errorReporter);
        final Node ast = result.ast;
        return ast;
    }

    public Node parse(String fileName, final String content) throws IOException {
        return parse(SourceFile.fromCode(fileName, content));
    }

    public Node parse(String filename, final InputStream inputStream, final Charset charset) throws IOException {
        return parse(SourceFile.fromInputStream(filename, inputStream, charset));
    }
}
