package org.slieb.jscodebuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class JsFileBuilder {

    private final File file;

    private final JsCodeBuilder jsCodeBuilder;

    private final JsCodePrinter jsCodePrinter;

    public JsFileBuilder(final File file) {
        this.file = file;
        this.jsCodeBuilder = new JsCodeBuilder();
        this.jsCodePrinter = new JsCodePrinter();
    }

    public JsCodeBuilder code() {
        return jsCodeBuilder;
    }

    public void build() throws IOException {
        File parent = file.getParentFile();
        if (parent.exists() || parent.mkdirs()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsCodePrinter.print(jsCodeBuilder.build()));
            }
        }
    }
}
